package it.unisannio.ingsw24.unpacked.logic;

import it.unisannio.ingsw24.entities.UnPackedFood;

import java.util.List;


public interface UnPackedLogic {

    int getNextId();

    int createUnPackedMySQL(String name, int averageExpiryDays, String category);

    UnPackedFood getUnPackedFood(String name);

    List<String> getAllUnPackedMySQLNames();

    boolean updateUnPackedMySQL(int ID, int averageExpiryDays);

    boolean deleteUnPackedMySQL(int ID);

}
