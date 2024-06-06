package it.unisannio.ingsw24.gateway.logic;

import it.unisannio.ingsw24.entities.PackedFood;
import it.unisannio.ingsw24.entities.UnPackedFood;

import java.util.List;


public interface GatewayLogic {

    UnPackedFood getUnPackedFood(String name, boolean isFridge, int quantity);
    PackedFood getPackedFood(String barcode, String date, boolean isFridge, int quantity);

    List<String> getAllUnPackedFoodNames();


}
