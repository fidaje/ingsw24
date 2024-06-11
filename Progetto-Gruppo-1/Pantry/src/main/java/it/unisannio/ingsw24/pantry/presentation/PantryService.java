package it.unisannio.ingsw24.pantry.presentation;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.PackedFood;
import it.unisannio.ingsw24.entities.Pantry;
import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.pantry.logic.PantryLogic;
import it.unisannio.ingsw24.pantry.logic.PantryLogicImplementation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pantry")
public class PantryService {

    PantryLogic logic;

    public PantryService(){
        this.logic = new PantryLogicImplementation();
    }

    @POST
    public Response createPantry(Pantry pantry){

        int pantryID = logic.createPantry(pantry);

        if (pantryID != 0){
            URI uri = UriBuilder.fromPath("/users/{pantryID}").build(pantryID);
            return Response.created(uri).build();
        }
        else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{pantryId}")
    public Response getPantry(@PathParam("pantryId") int pantryId){
        Pantry pantry = logic.getPantry(pantryId);
        return Response.ok(pantry).build();
    }

    @GET
    @Path("/pantries/{username}")
    public Response getPantries(@PathParam("username") String ownerUsername){
        List<Pantry> pantries = logic.getPantries(ownerUsername);

        if (pantries != null)
            return Response.ok(pantries).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{pantryId}/foods")
    public Response getFoods(@PathParam("pantryId") int pantryId){
        List<Food> foods = logic.getFoods(pantryId);

        if (foods != null)
            return Response.ok(foods).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{pantryId}/expiredFoods")
    public Response getExpiredFoods(@PathParam("pantryId") int pantryId){
        List<Food> foods = logic.getExpiredFoods(pantryId);

        if (foods != null)
            return Response.ok(foods).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @PUT
    @Path("/{pantryId}/foods/unpacked")
    public Response updateFoods(@PathParam("pantryId") int pantryId, UnPackedFood f){
        boolean result = logic.updateFoods(pantryId, f);
        System.out.println(f);
        if (result) return Response.ok(f).build();
        else return Response.serverError().build();
    }

    @PUT
    @Path("/{pantryId}/foods/packed")
    public Response updateFoods(@PathParam("pantryId") int pantryId, PackedFood f){
        boolean result = logic.updateFoods(pantryId, f);
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{pantryId}/guests")
    public Response updateGuests(@PathParam("pantryId") int pantryId, String username){
        boolean result = logic.updateGuests(pantryId, username);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("{pantryId}/foods/{foodName}")
    public Response deleteFoodByName(@PathParam("pantryId") int pantryId, @PathParam("foodName") String foodName){
        boolean result = logic.deleteFoodByName(pantryId, foodName);
        if (result) return Response.noContent().build();
        return Response.serverError().build();
    }

    @DELETE
    @Path("{pantryId}/guests/{username}")
    public Response deleteGuestByUsername(@PathParam("pantryId") int pantryId, @PathParam("username") String username){
        boolean result = logic.deleteGuestByUsername(pantryId, username);
        if (result) return Response.noContent().build();
        return Response.serverError().build();
    }

    @DELETE
    @Path("{pantryId}")
    public Response deletePantry(@PathParam("pantryId") int pantryId){
        boolean result = logic.deletePantry(pantryId);
        if (result) return Response.noContent().build();
        return Response.serverError().build();
    }
}