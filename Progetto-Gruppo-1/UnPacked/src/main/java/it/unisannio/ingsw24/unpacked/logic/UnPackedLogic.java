package it.unisannio.ingsw24.unpacked.logic;

import it.unisannio.ingsw24.unpacked.persistance.UnPackedMySQL;

import java.util.Map;


public interface UnPackedLogic {

    boolean createFoodDAO(String ID, String name, int averageExpiryDays, String category);
    UnPackedMySQL getFoodDAO(String name);
    Map<String, UnPackedMySQL> getAllFoodDAO();
    boolean updateFoodDAO(String ID, int averageExpiryDays);
    boolean deleteFoodDAO(String ID);

}
