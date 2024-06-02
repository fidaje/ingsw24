package it.unisannio.ingsw24.gateway.logic;

import it.unisannio.ingsw24.entities.OpenFood;
import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.entities.UnPackedMySQL;

import java.util.Map;

public interface GatewayLogic {

    UnPackedFood getUnPackedFood(String name, boolean isFridge, int quantity);
    OpenFood getOpenFood();

    Map<String, UnPackedMySQL> getAllUnPackedFood();


}
