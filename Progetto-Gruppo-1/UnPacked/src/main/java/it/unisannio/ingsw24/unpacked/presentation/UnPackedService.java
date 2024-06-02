package it.unisannio.ingsw24.unpacked.presentation;

import it.unisannio.ingsw24.unpacked.logic.UnPackedLogic;
import it.unisannio.ingsw24.unpacked.logic.UnPackedLogicImplementation;
import it.unisannio.ingsw24.entities.UnPackedMySQL;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/unpacked")
public class UnPackedService {

    UnPackedLogic logic;

    public UnPackedService() {
        this.logic = new UnPackedLogicImplementation();
    }

    @GET
    public Response getAllUnPackedMySQL(){
        Map<String, UnPackedMySQL> unPackedMySQLs = logic.getAllUnPackedMySQL();
        return Response.ok(unPackedMySQLs).build();
    }

    @GET
    @Path("{name}")
    public Response getUnPackedMySQL(@PathParam("name") String name){
        UnPackedMySQL f = logic.getUnPackedMySQL(name);
        if (f == null)
            return Response.status(404, "Food " + name + " doesn't exist").build();
        return Response.ok(f).build();
    }

   @PUT
   @Path("/{ID}/{days}")
   public Response updateUnPackedMySQL(@PathParam("ID") int ID, @PathParam("days") int days){
        boolean result = logic.updateUnPackedMySQL(ID, days);

        if (result){
            return Response.ok().build();
        }
        else
            return Response.status(400,"The resource to update does not exists").build();
   }




}
