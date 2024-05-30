package it.unisannio.ingsw24.unpacked.logic;

import java.util.Map;

import it.unisannio.ingsw24.entities.UnPackedFood;

public interface UnPackedLogic {

    boolean createUnPackedFood(String ID, String name, int averageExpiryDays, String category);
    UnPackedFood getUnPackedFood(String name);
    Map<String, UnPackedFood> getAllUnPackedFood();
    boolean updateUnPackedFood(String ID, int averageExpiryDays);
    boolean deleteUnPackedFood(String ID);

}
