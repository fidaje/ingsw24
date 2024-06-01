package it.unisannio.ingsw24.gateway.presentation;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.gateway.logic.GatewayLogic;
import it.unisannio.ingsw24.gateway.logic.GatewayLogicImplementation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
        UnPackedFood upf = logic.getUnPackedFood(name);
        if (upf == null)
            return Response.status(404, "Food " + name + " doesn't exist").build();
        return Response.ok(upf).build();
    }
}
