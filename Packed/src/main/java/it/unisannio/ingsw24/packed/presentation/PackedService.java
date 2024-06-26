package it.unisannio.ingsw24.packed.presentation;

import it.unisannio.ingsw24.entities.PackedFood;
import it.unisannio.ingsw24.packed.logic.PackedLogic;
import it.unisannio.ingsw24.packed.logic.PackedLogicImplemetation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * The PackedService class is the RESTful service for the PackedFood entity.
 * It is responsible for the communication between the client and the server.
 * The operations that can be performed are GET.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/packed")
public class PackedService {

    PackedLogic logic;

    /**
     * This constructor creates a new PackedLogicImplementation object.
     */
    public PackedService(){
        this.logic = new PackedLogicImplemetation();
    }

    /**
     * This method returns the PackedFood object with the given barcode.
     * @param barcode the barcode of the PackedFood object.
     * @return the PackedFood object with the given barcode.
     */
    @GET
    @Path("{barcode}")
    public PackedFood getPackedFood(@PathParam("barcode") String barcode){
        return this.logic.getPackedFood(barcode);
    }
}
