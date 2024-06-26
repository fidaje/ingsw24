package it.unisannio.ingsw24.packed.logic;

import it.unisannio.ingsw24.entities.PackedFood;

/**
 * This interface provides the methods to interact with the packed food database.
 */
public interface PackedLogic {

    /**
     * This method returns the packed food with the given barcode.
     * @param barcode the barcode of the packed food
     * @return the packed food with the given barcode
     */
    PackedFood getPackedFood(String barcode);


}
