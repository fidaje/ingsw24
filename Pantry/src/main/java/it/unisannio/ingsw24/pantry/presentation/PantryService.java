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


/**
 * This class is the RESTful service for the Pantry entity.
 * It is responsible for the communication between the client and the server.
 * The operations that can be performed are POST, GET, PUT and DELETE.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pantry")
public class PantryService {

    PantryLogic logic;

    /**
     * This constructor creates a new PantryLogicImplementation object.
     */
    public PantryService(){
        this.logic = new PantryLogicImplementation();
    }

    /**
     * This method creates a new Pantry object in the database.
     * @param pantry the Pantry object to be created.
     * @return a response with the status of the operation.
     */
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

    /**
     * This method returns the Pantry object with the given id.
     * @param pantryId the id of the Pantry object.
     * @return the Pantry object with the given id.
     */
    @GET
    @Path("/{pantryId}")
    public Response getPantry(@PathParam("pantryId") int pantryId){
        Pantry pantry = logic.getPantry(pantryId);
        return Response.ok(pantry).build();
    }

    /**
     * This method returns a list of all the Pantry of the given owner.
     * @return a list of all the Pantry of the given owner.
     */
    @GET
    @Path("/pantries/{username}")
    public Response getPantries(@PathParam("username") String ownerUsername){
        List<Pantry> pantries = logic.getPantries(ownerUsername);

        if (pantries != null)
            return Response.ok(pantries).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * This method returns a list of all the foods in the Pantry object with the given id.
     * @param pantryId
     * @return a list of all the foods in the Pantry object with the given id.
     */
    @GET
    @Path("{pantryId}/foods")
    public Response getFoods(@PathParam("pantryId") int pantryId){
        List<Food> foods = logic.getFoods(pantryId);

        if (foods != null)
            return Response.ok(foods).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * This method returns the food with the given name in the Pantry object with the given id.
     * @param pantryId the id of the Pantry object.
     * @param foodName the name of the Food object.
     * @return the food with the given name in the Pantry object with the given id.
     */
    @GET
    @Path("{pantryId}/foods/{foodName}")
    public Response getFoodByName(@PathParam("pantryId") int pantryId, @PathParam ("foodName") String foodName){
        Food food = logic.getFoodByName(pantryId, foodName.toLowerCase());
        if (food != null)
            return Response.ok(food).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * This method returns a list of all the expired foods in the Pantry object with the given id.
     * @param pantryId
     * @return a list of all the expired foods in the Pantry object with the given id.
     */
    @GET
    @Path("{pantryId}/expiredFoods")
    public Response getExpiredFoods(@PathParam("pantryId") int pantryId){
        List<Food> foods = logic.getExpiredFoods(pantryId);

        if (foods != null)
            return Response.ok(foods).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * This method checks if the given username is present in the Pantry object with the given id.
     * @param pantryId
     * @param username
     * @return a response with the status of the operation.
     */
    @GET
    @Path("/check/{pantryId}/{username}")
    public Response checkUsername(@PathParam("pantryId") int pantryId, @PathParam("username") String username){
        if (logic.checkUsername(pantryId, username))
            return Response.ok().build();
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    /**
     * This method checks if the given username is the owner of the Pantry object with the given id.
     * @param pantryId
     * @param username
     * @return a response with the status of the operation.
     */
    @GET
    @Path("/check/owner/{pantryId}/{username}")
    public Response checkOwner(@PathParam("pantryId") int pantryId, @PathParam("username") String username) {
        if (logic.checkOwner(pantryId, username))
            return Response.ok().build();
        return Response.status(Response.Status.FORBIDDEN).build();
    }


    /**
     * This method updates the Pantry object with the given id with the unpacked food.
     * @param pantryId the id of the Pantry object.
     * @param f the UnPackedFood object to be updated.
     * @return a response with the status of the operation.
     */
    @PUT
    @Path("/{pantryId}/foods/unpacked")
    public Response updateFoods(@PathParam("pantryId") int pantryId, UnPackedFood f){
        boolean result = logic.updateFoods(pantryId, f);
        if (result) return Response.ok(f).build();
        else return Response.serverError().build();
    }

    /**
     * This method updates the Pantry object with the given id with the packed food.
     * @param pantryId the id of the Pantry object.
     * @param f the PackedFood object to be updated.
     * @return a response with the status of the operation.
     */
    @PUT
    @Path("/{pantryId}/foods/packed")
    public Response updateFoods(@PathParam("pantryId") int pantryId, PackedFood f){
        boolean result = logic.updateFoods(pantryId, f);
        return Response.ok(result).build();
    }

    /**
     * This method add to the Pantry object the given username of the guest.
     * @param pantryId the id of the Pantry object.
     * @param username the username to be updated.
     * @return a response with the status of the operation.
     */
    @PUT
    @Path("/{pantryId}/guests")
    public Response updateGuests(@PathParam("pantryId") int pantryId, String username){
        boolean result = logic.updateGuests(pantryId, username);
        if (result) return Response.ok().build();
        else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * This method deletes the food with the given name in the Pantry object with the given id.
     * @param pantryId the id of the Pantry object.
     * @param foodName the name of the Food object.
     * @return a response with the status of the operation.
     */
    @DELETE
    @Path("{pantryId}/foods/{foodName}")
    public Response deleteFoodByName(@PathParam("pantryId") int pantryId, @PathParam("foodName") String foodName){
        boolean result = logic.deleteFoodByName(pantryId, foodName);
        if (result) return Response.noContent().build();
        return Response.serverError().build();
    }

    /**
     * This method deletes the guest with the given username in the Pantry object with the given id.
     * @param pantryId the id of the Pantry object.
     * @param username the username of the guest.
     * @return a response with the status of the operation.
     */
    @DELETE
    @Path("{pantryId}/guests/{username}")
    public Response deleteGuestByUsername(@PathParam("pantryId") int pantryId, @PathParam("username") String username){
        boolean result = logic.deleteGuestByUsername(pantryId, username);
        if (result) return Response.noContent().build();
        return Response.serverError().build();
    }

    /**
     * This method deletes the Pantry object with the given id.
     * @param pantryId the id of the Pantry object.
     * @return a response with the status of the operation.
     */
    @DELETE
    @Path("{pantryId}")
    public Response deletePantry(@PathParam("pantryId") int pantryId){
        boolean result = logic.deletePantry(pantryId);
        if (result) return Response.noContent().build();
        return Response.serverError().build();
    }
}