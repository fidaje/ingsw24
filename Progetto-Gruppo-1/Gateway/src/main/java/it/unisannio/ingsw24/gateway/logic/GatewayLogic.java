package it.unisannio.ingsw24.gateway.logic;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.PackedFood;
import it.unisannio.ingsw24.entities.Pantry;
import it.unisannio.ingsw24.entities.UnPackedFood;

import java.util.List;


public interface GatewayLogic {

    Pantry getPantry(int pantryId);

    List<Pantry> getPantries(String ownerUsername);

    List<Food> getFoods(int pantryId);

    List<Food> getExpiredFoods(int pantryId);

    Integer updateFoods(int pantryId, Food f);

    boolean updateGuests(int pantryId, String username);

}
