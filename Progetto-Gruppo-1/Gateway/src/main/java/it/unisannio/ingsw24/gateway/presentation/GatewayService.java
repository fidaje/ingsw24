package it.unisannio.ingsw24.gateway.presentation;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.gateway.logic.GatewayLogic;
import it.unisannio.ingsw24.gateway.logic.GatewayLogicImplementation;
import it.unisannio.ingsw24.entities.UnPackedMySQL;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/gateway")
public class GatewayService {

    GatewayLogic logic;

    public GatewayService(){
        this.logic = new GatewayLogicImplementation();
    }

    @GET
    @Path("{name}")
    public Response getUnPackedFood(@PathParam("name") String name){
        boolean isFridge = false;
        int quantity = 1;
        UnPackedFood upf = logic.getUnPackedFood(name, isFridge, quantity);
        if (upf == null)
            return Response.status(404, "Food " + name + " doesn't exist").build();
        return Response.ok(upf).build();
    }

    @GET
    public Response getAllUnPackedMySQL(){
        Map<String, UnPackedMySQL> m = logic.getAllUnPackedFood();
        return Response.ok(m).build();
    }
}
