package it.unisannio.ingsw24.unpacked.presentation;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.unpacked.logic.UnPackedLogic;
import it.unisannio.ingsw24.unpacked.logic.UnPackedLogicImplementation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/unpackedfood/api")
public class UnPackedService {

    UnPackedLogic logic;

    public UnPackedService() {
        this.logic = new UnPackedLogicImplementation();
    }

    @GET
    @Path("{name}")
    public Response getUnpackedFood(@PathParam("name") String name){
        UnPackedFood unp = logic.getUnPackedFood(name);
        return Response.ok(unp).build();

    }

   @PUT
   @Path("/{ID}/{days}")
   public Response updateUnpackedFood(@PathParam("ID") String ID, @PathParam("days") int days){
        boolean result = logic.updateUnPackedFood(ID, days);

        if (result == true){
            return Response.ok().build();
        }
        else
            return Response.status(400,"The resource to update does not exists").build();

   }
}
