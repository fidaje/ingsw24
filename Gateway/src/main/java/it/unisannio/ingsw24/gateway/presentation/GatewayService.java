package it.unisannio.ingsw24.gateway.presentation;

import it.unisannio.ingsw24.entities.*;
import it.unisannio.ingsw24.gateway.exception.UserNotValidException;
import it.unisannio.ingsw24.gateway.logic.GatewayLogic;
import it.unisannio.ingsw24.gateway.logic.GatewayLogicImplementation;
import it.unisannio.ingsw24.gateway.security.PasswordEncoderBase64;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import java.net.URI;
import java.util.List;

/**
 * This class is the RESTful service for the Gateway entity.
 * It is responsible for the communication between the client and the server.
 * The operations that can be performed are POST, GET, PUT and DELETE.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/gateway")
public class GatewayService {

    GatewayLogic logic;

    /**
     * This constructor creates a new GatewayLogicImplementation object.
     */
    public GatewayService(){
        this.logic = new GatewayLogicImplementation();
    }

    /**
     * This method creates a new user.
     * @param user the user to be created.
     * @return a response with the status of the operation.
     */
    @POST
    @Path("/user")
    public Response createUser(MyUser user){

        String encodedPassword = new PasswordEncoderBase64().encode(user.getPassword());
        user.setPassword(encodedPassword);

        String username = logic.createUser(user);
        if (username == null)
            return Response.serverError().build();
        URI uri = UriBuilder.fromPath("/{username}").build(username);
        return Response.created(uri).build();
    }

    /**
     * This method creates a new pantry
     * @return a response with the status of the operation.
     */
    @POST
    @Path("/pantry")
    public Response createPantry(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String ownerUsername = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + ownerUsername);

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
    @Path("/users")
    @RolesAllowed({"OWNER"})
    public Response updatePassword(String password){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        String encodedPassword = new PasswordEncoderBase64().encode(password);

        if (logic.updatePassword(username, encodedPassword))
            return Response.ok().build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * This method adds a new UnPackedFood object to the pantry with the given id.
     * @param pantryId the id of the pantry.
     * @param name the name of the UnPackedFood object.
     * @param isFridge the boolean value that indicates if the food is in the fridge.
     * @param quantity the quantity of the food.
     */
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

    /**
     * This method add a packed food to the pantry with the given id and the given barcode.
     * @param pantryId the id of the pantry.
     * @param barcode the barcode of the packed food.
     * @param isFridge the boolean value that indicates if the food is in the fridge.
     * @param quantity the quantity of the food.
     * @return a response with the status of the operation.
     */
    @PUT
    @Path("/{pantryId}/foods/packed/{barcode}")
    @RolesAllowed({"OWNER", "GUEST"})
    public Response updateFoodsWithPacked(@PathParam("pantryId") int pantryId, @PathParam("barcode") String barcode, @QueryParam("isFridge") boolean isFridge, @QueryParam("quantity") int quantity, @QueryParam("expirationDate") String expirationDate){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        System.out.println("username in method = " + username);

        if (logic.checkUsername(pantryId, username)) {
            PackedFood pf = logic.getPackedFood(barcode);
            pf.setIsFridge(isFridge);
            pf.setQuantity(quantity);
            pf.setExpirationDate(expirationDate);
            Integer result = logic.updateFoods(pantryId, pf, "packed");
            if (result != null) return Response.ok(result).build();
            else return Response.serverError().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    /**
     * This method updates the guests of the pantry with the given id, if the user is the owner.
     * @param pantryId the id of the pantry.
     * @param guestUsername the username of the guest.
     * @return a response with the status of the operation.
     */
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

    /**
     * This method deletes the food with the given name from the pantry
     * @param pantryId the id of the pantry.
     * @param foodName the name of the food.
     * @return a response with the status of the operation.
     */
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

    /**
     * This method deletes the guest with the given username from the pantry, if the user is the owner.
     * @param pantryId
     * @param guestUsername
     * @return a response with the status of the operation.
     */
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

    /**
     * This method deletes the pantry with the given id, if the user is the owner.
     * @param pantryId
     * @return a response with the status of the operation.
     */
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
