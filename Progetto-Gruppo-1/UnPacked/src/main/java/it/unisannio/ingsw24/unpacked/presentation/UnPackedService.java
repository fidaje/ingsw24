package it.unisannio.ingsw24.unpacked.presentation;

import it.unisannio.ingsw24.unpacked.logic.UnPackedLogic;
import it.unisannio.ingsw24.unpacked.logic.UnPackedLogicImplementation;
import it.unisannio.ingsw24.unpacked.persistance.FoodDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/FoodDAO")
public class UnPackedService {

    UnPackedLogic logic;

    public UnPackedService() {
        this.logic = new UnPackedLogicImplementation();
    }

    @GET
    @Path("{name}")
    public Response getFoodDAO(@PathParam("name") String name){
        FoodDAO f = logic.getFoodDAO(name);
        return Response.ok(f).build();

    }

   @PUT
   @Path("/{ID}/{days}")
   public Response updateFoodDAO(@PathParam("ID") String ID, @PathParam("days") int days){
        boolean result = logic.updateFoodDAO(ID, days);

        if (result){
            return Response.ok().build();
        }
        else
            return Response.status(400,"The resource to update does not exists").build();

   }


}
