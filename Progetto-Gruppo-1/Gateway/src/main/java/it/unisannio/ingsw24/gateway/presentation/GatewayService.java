package it.unisannio.ingsw24.gateway.presentation;

import it.unisannio.ingsw24.entities.PackedFood;
import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.gateway.logic.GatewayLogic;
import it.unisannio.ingsw24.gateway.logic.GatewayLogicImplementation;
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

    //forse è POST
    @GET
    @Path("/unpacked/{name}")
    public Response getUnPackedFood(@PathParam("name") String name, @QueryParam("isFridge") boolean isFridge, @QueryParam("quantity") int quantity){
        UnPackedFood upf = logic.getUnPackedFood(name, isFridge, quantity);
        if (upf == null)
            return Response.status(404, "Food " + name + " doesn't exist").build();
        return Response.ok(upf).build();
    }

    @GET
    @Path("/openfood/{barcode}")
    public Response getOpenFood(@PathParam("barcode") String barcode, @QueryParam("date") String date, @QueryParam("isFridge") boolean isFridge, @QueryParam("quantity") int quantity){

        PackedFood ofp = logic.getPackedFood(barcode, date, isFridge, quantity);
        if (ofp == null)
            return Response.status(404, "Food with code " + barcode + " doesn't exist").build();

        return Response.ok(ofp).build();
    }

    @GET
    public Response getAllUnPackedNames(){
        List<String> m = logic.getAllUnPackedFoodNames();
        return Response.ok(m).build();
    }
}
