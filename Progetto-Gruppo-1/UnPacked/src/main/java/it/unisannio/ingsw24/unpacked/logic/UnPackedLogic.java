package it.unisannio.ingsw24.unpacked.logic;

import it.unisannio.ingsw24.unpacked.persistance.FoodDAO;

import java.util.Map;


public interface UnPackedLogic {

    boolean createFoodDAO(String ID, String name, int averageExpiryDays, String category);
    FoodDAO getFoodDAO(String name);
    Map<String, FoodDAO> getAllFoodDAO();
    boolean updateFoodDAO(String ID, int averageExpiryDays);
    boolean deleteFoodDAO(String ID);

}
