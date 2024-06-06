package it.unisannio.ingsw24.unpacked.logic;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.entities.UnPackedMySQL;

import java.util.Map;


public interface UnPackedLogic {

    int createUnPackedMySQL(String name, int averageExpiryDays, String category);
    UnPackedFood getUnPackedFood(String name);
    Map<String, UnPackedMySQL> getAllUnPackedMySQL();
    boolean updateUnPackedMySQL(int ID, int averageExpiryDays);
    boolean deleteUnPackedMySQL(int ID);

}
