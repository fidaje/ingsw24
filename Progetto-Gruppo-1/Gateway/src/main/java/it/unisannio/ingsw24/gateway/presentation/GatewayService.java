package it.unisannio.ingsw24.gateway.presentation;

import it.unisannio.ingsw24.entities.*;
import it.unisannio.ingsw24.gateway.exception.UserNotValidException;
import it.unisannio.ingsw24.gateway.logic.GatewayLogic;
import it.unisannio.ingsw24.gateway.logic.GatewayLogicImplementation;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/gateway")
public class GatewayService {

    GatewayLogic logic;

    public GatewayService(){
        this.logic = new GatewayLogicImplementation();
    }

    @POST
    @Path("/user")
    public Response createUser(MyUser user){
        String username = logic.createUser(user);
        if (username == null)
            return Response.serverError().build();
        URI uri = UriBuilder.fromPath("/{username}").build(username);
        return Response.created(uri).build();
    }

    @POST
    @Path("/pantry/{ownerUsername}")
    public Response createPantry(@PathParam("ownerUsername") String ownerUsername){
        Pantry pantry = new Pantry(ownerUsername);
        int pantryId = logic.createPantry(pantry);
        if (pantryId == 0)
            return Response.serverError().build();
        URI uri = UriBuilder.fromPath("/{pantryId}").build(pantryId);
        return Response.created(uri).build();
    }

    @GET
    @Path("/user/{username}")
    public Response getUser(@PathParam("username") String username){
        MyUser user = logic.getUser(username);
        if (user == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user).build();
    }

    @GET
    @Path("pantry/{pantryId}")
    @RolesAllowed({"OWNER", "GUEST"})
    public Response getPantry(@PathParam("pantryId") int pantryId){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        Pantry pantry = logic.getPantry(pantryId);

        if (pantry == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        boolean checkOwner = username.equals(pantry.getOwnerUsername());
        boolean checkGuest = pantry.getGuestsUsernames().contains(username);

        try {
            if (!checkOwner && !checkGuest)
                throw new UserNotValidException("User isn't owner or guest");
        } catch (UserNotValidException e){
                e.printStackTrace();
                return Response.status(Response.Status.FORBIDDEN).build();
            }

        return Response.ok(pantry).build();
    }

    @GET
    @Path("pantries")
    @RolesAllowed({"OWNER", "GUEST"})
    public Response getPantries(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        List<Pantry> pantries = logic.getPantries(username);
        if (pantries == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(pantries).build();
    }

    @GET
    @Path("{pantryId}/foods")
    @RolesAllowed({"OWNER", "GUEST"})
    public Response getFoods(@PathParam("pantryId") int pantryId){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkUsername(pantryId, username)){
            List<Food> foods = logic.getFoods(pantryId);
            if (foods == null)
                return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(foods).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("{pantryId}/foods/{foodName}")
    @RolesAllowed({"OWNER", "GUEST"})
    public Response getFoodFromPantryByName(@PathParam("pantryId") int pantryId, @PathParam ("foodName") String foodName){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkUsername(pantryId, username)) {
            Food food = logic.getFoodFromPantryByName(pantryId, foodName);
            if (food == null)
                return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(food).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("{pantryId}/expiredFoods")
    @RolesAllowed({"OWNER", "GUEST"})
    public Response getExpiredFoods(@PathParam("pantryId") int pantryId){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkUsername(pantryId, username)) {
            List<Food> foods = logic.getExpiredFoods(pantryId);
            if (foods == null)
                return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(foods).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Path("/{pantryId}/foods/unpacked/{name}")
    @RolesAllowed({"OWNER", "GUEST"})
    public Response updateFoodsWithUnPacked(@PathParam("pantryId") int pantryId, @PathParam("name") String name, @QueryParam("isFridge") boolean isFridge, @QueryParam("quantity") int quantity){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkUsername(pantryId, username)) {
            UnPackedFood upf = logic.getUnPackedFood(name);
            upf.setIsFridge(isFridge);
            upf.setQuantity(quantity);
            Integer result = logic.updateFoods(pantryId, upf, "unpacked");
            if (result != null) return Response.ok(result).build();
            else return Response.serverError().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Path("/{pantryId}/foods/packed/{barcode}")
    @RolesAllowed({"OWNER", "GUEST"})
    public Response updateFoodsWithPacked(@PathParam("pantryId") int pantryId, @PathParam("barcode") String barcode, @QueryParam("isFridge") boolean isFridge, @QueryParam("quantity") int quantity){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkUsername(pantryId, username)) {
            PackedFood pf = logic.getPackedFood(barcode);
            pf.setIsFridge(isFridge);
            pf.setQuantity(quantity);
            Integer result = logic.updateFoods(pantryId, pf, "packed");
            if (result != null) return Response.ok(result).build();
            else return Response.serverError().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Path("/{pantryId}/guests")
    @RolesAllowed({"OWNER"})
    public Response updateGuests(@PathParam("pantryId") int pantryId, String guestUsername){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkOwner(pantryId, username)) {
            boolean result = logic.updateGuests(pantryId, guestUsername);
            if (result) return Response.ok(result).build();
            else return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("{pantryId}/foods/{foodName}")
    @RolesAllowed({"OWNER", "GUEST"})
    public Response deleteFoodByName(@PathParam("pantryId") int pantryId, @PathParam("foodName") String foodName){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkUsername(pantryId, username)) {
            boolean result = logic.deleteFoodByName(pantryId, foodName);
            if (result) return Response.noContent().build();
            return Response.serverError().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("{pantryId}/guests/{username}")
    @RolesAllowed({"OWNER"})
    public Response deleteGuestByUsername(@PathParam("pantryId") int pantryId, @PathParam("username") String guestUsername){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkOwner(pantryId, username)) {
            boolean result = logic.deleteGuestByUsername(pantryId, guestUsername);
            if (result) return Response.noContent().build();
            return Response.serverError().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("{pantryId}")
    @RolesAllowed({"OWNER"})
    public Response deletePantry(@PathParam("pantryId") int pantryId){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkOwner(pantryId, username)) {
            boolean result = logic.deletePantry(pantryId);
            if (result) return Response.noContent().build();
            return Response.serverError().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
