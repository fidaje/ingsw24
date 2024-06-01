package it.unisannio.ingsw24.gateway.logic;

import it.unisannio.ingsw24.entities.OpenFood;
import it.unisannio.ingsw24.entities.UnPackedFood;

public interface GatewayLogic {

    UnPackedFood getUnPackedFood(String name);
    OpenFood getOpenFood();


}
