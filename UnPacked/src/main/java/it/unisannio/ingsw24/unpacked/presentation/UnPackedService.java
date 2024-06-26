package it.unisannio.ingsw24.unpacked.presentation;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.unpacked.logic.UnPackedLogic;
import it.unisannio.ingsw24.unpacked.logic.UnPackedLogicImplementation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * This class is the RESTful service for the UnPackedFood entity.
 * It is responsible for the communication between the client and the server.
 * The operations that can be performed are GET and PUT.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/unpacked")
public class UnPackedService {

    UnPackedLogic logic;

    /**
     * This constructor creates a new UnPackedLogicImplementation object.
     */
    public UnPackedService() {
        this.logic = new UnPackedLogicImplementation();
    }

    /**
     * This method returns a list of all the UnPackedFood objects in the database.
     * @return a list of String of all the UnPackedFood objects in the database.
     */
    @GET
    public Response getAllUnPackedMySQL(){
        List<String> unPackedMySQLs = logic.getAllUnPackedMySQLNames();
        return Response.ok(unPackedMySQLs).build();
    }

    /**
     * This method returns the UnPackedFood object with the given name.
     * @param name the name of the UnPackedFood object.
     * @return the UnPackedFood object with the given name.
     */
    @GET
    @Path("{name}")
    public Response getUnPackedFood(@PathParam("name") String name){
        UnPackedFood f = logic.getUnPackedFood(name);
        if (f == null)
            return Response.status(404, "Food " + name + " doesn't exist").build();
        return Response.ok(f).build();
    }

   /**
    * This method updates the UnPackedFood object with the given id.
    * @param ID the id of the UnPackedFood object.
    * @param days the new average expiry days of the UnPackedFood object.
    * @return a response with the status of the operation.
    */
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
