package it.unisannio.ingsw24.gateway.logic;

import it.unisannio.ingsw24.entities.PackedFood;
import it.unisannio.ingsw24.entities.Pantry;
import it.unisannio.ingsw24.entities.UnPackedFood;

import java.util.List;


public interface GatewayLogic {

    Pantry getPantry(int pantryId);


}
