package it.unisannio.ingsw24.gateway.logic;

import it.unisannio.ingsw24.entities.*;

import java.util.List;


public interface GatewayLogic {

    String createUser(MyUser user);

    int createPantry(Pantry pantry);

    MyUser getUser(String username);

    Pantry getPantry(int pantryId);

    List<Pantry> getPantries(String ownerUsername);

    List<Food> getFoods(int pantryId);

    Food getFoodFromPantryByName(int pantryId, String name);

    List<Food> getExpiredFoods(int pantryId);

    UnPackedFood getUnPackedFood(String name);

    PackedFood getPackedFood(String barcode);

    Integer updateFoods(int pantryId, Food f, String type);

    boolean updateGuests(int pantryId, String username);

    boolean deleteFoodByName(int pantryId, String foodName);

    boolean deleteGuestByUsername(int pantryId, String username);

    boolean deletePantry(int pantryId);

}
