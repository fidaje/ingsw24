package it.unisannio.ingsw24.packed.presentation;

import it.unisannio.ingsw24.entities.PackedFood;
import it.unisannio.ingsw24.packed.logic.PackedLogic;
import it.unisannio.ingsw24.packed.logic.PackedLogicImplemetation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/packed")
public class PackedService {

    PackedLogic logic;

    public PackedService(){
        this.logic = new PackedLogicImplemetation();
    }

    @GET
    @Path("{barcode}")
    public PackedFood getPackedFood(@PathParam("barcode") String barcode){
        return this.logic.getPackedFood(barcode);
    }
}
