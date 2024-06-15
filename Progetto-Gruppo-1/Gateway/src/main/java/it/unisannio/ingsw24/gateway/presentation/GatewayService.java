package it.unisannio.ingsw24.gateway.presentation;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.PackedFood;
import it.unisannio.ingsw24.entities.Pantry;
import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.gateway.logic.GatewayLogic;
import it.unisannio.ingsw24.gateway.logic.GatewayLogicImplementation;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/gateway")
public class GatewayService {

    GatewayLogic logic;

    public GatewayService(){
        this.logic = new GatewayLogicImplementation();
    }

    @GET
    @Path("{pantryId}")
    public Response getPantry(@PathParam("pantryId") int pantryId){
        Pantry pantry = logic.getPantry(pantryId);
        if (pantry == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(pantry).build();
    }

    @GET
    @Path("pantries/{username}")
    public Response getPantries(@PathParam("username") String ownerUsername){
        List<Pantry> pantries = logic.getPantries(ownerUsername);
        if (pantries == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(pantries).build();
    }

    @GET
    @Path("{pantryId}/foods")
    public Response getFoods(@PathParam("pantryId") int pantryId){
        List<Food> foods = logic.getFoods(pantryId);
        if (foods == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(foods).build();
    }

    @GET
    @Path("{pantryId}/expiredFoods")
    public Response getExpiredFoods(@PathParam("pantryId") int pantryId){
        List<Food> foods = logic.getExpiredFoods(pantryId);
        if (foods == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(foods).build();
    }

    @PUT
    @Path("/{pantryId}/foods/unpacked/{name}")
    public Response updateFoodsWithUnPacked(@PathParam("pantryId") int pantryId, @PathParam("name") String name, @QueryParam("isFridge") boolean isFridge, @QueryParam("quantity") int quantity){
        UnPackedFood upf = logic.getUnPackedFood(name);
        upf.setIsFridge(isFridge);
        upf.setQuantity(quantity);
        Integer result = logic.updateFoods(pantryId, upf, "unpacked");
        if (result != null) return Response.ok(result).build();
        else return Response.serverError().build();
    }

    @PUT
    @Path("/{pantryId}/foods/packed/{barcode}")
    public Response updateFoodsWithPacked(@PathParam("pantryId") int pantryId, @PathParam("barcode") String barcode, @QueryParam("isFridge") boolean isFridge, @QueryParam("quantity") int quantity){
        PackedFood pf = logic.getPackedFood(barcode);
        pf.setIsFridge(isFridge);
        pf.setQuantity(quantity);
        Integer result = logic.updateFoods(pantryId, pf, "packed");
        if (result != null) return Response.ok(result).build();
        else return Response.serverError().build();
    }


}
