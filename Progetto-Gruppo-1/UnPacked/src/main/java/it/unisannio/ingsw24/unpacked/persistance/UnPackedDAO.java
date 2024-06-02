package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.UnPackedMySQL;

import java.util.Map;

public interface UnPackedDAO {

    String DATABASE_NAME = "UnPackedDB";

    String TABLE = "UNPACKEDFOOD";

    String ELEMENT_NAME = "Name";

    String ELEMENT_AVERAGE_EXPIRY_DAYS = "Average_Expiry_Days";

    String ELEMENT_UNIQUE_ID = "Unique_ID";

    String ELEMENT_CATEGORY = "Category";

    boolean dropDB();

    int createUnPackedMySQL(String name, int averageExpiryDays, String category);

    Map<String, UnPackedMySQL> getAllUnPackedMySQL();

    public int getNextId();


    UnPackedMySQL getUnPackedMySQL(String name);

    // Vedere commento UnPackedDAOMySQL linea 141
    // FoodDAO updateFoodDAO(int averageExpiryDate);

    boolean updateUnPackedMySQL(int id, int averageExpiryDays);

    boolean deleteUnPackedMySQL(int id);

    boolean closeConnection();

}
