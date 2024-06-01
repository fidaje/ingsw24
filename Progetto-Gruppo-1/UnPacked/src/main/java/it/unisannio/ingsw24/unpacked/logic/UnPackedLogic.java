package it.unisannio.ingsw24.unpacked.logic;

import it.unisannio.ingsw24.unpacked.persistance.UnPackedMySQL;

import java.util.Map;


public interface UnPackedLogic {

    boolean createUnPackedMySQL(String ID, String name, int averageExpiryDays, String category);
    UnPackedMySQL getUnPackedMySQL(String name);
    Map<String, UnPackedMySQL> getAllUnPackedMySQL();
    boolean updateUnPackedMySQL(String ID, int averageExpiryDays);
    boolean deleteUnPackedMySQL(String ID);

}
