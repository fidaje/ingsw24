package it.unisannio.ingsw24.unpacked.persistance;

import java.util.Map;

public interface UnPackedDAO {

    String DATABASE_NAME = "UnPackedDB";

    String TABLE = "UNPACKEDFOOD";

    String ELEMENT_NAME = "Name";

    String ELEMENT_AVERAGE_EXPIRY_DAYS = "Average_Expiry_Days";

    String ELEMENT_UNIQUE_ID = "Unique_ID";

    String ELEMENT_CATEGORY = "Category";

    boolean dropDB();

    boolean createUnPackedMySQL(String ID, String name, int averageExpiryDays, String category);

    Map<String, UnPackedMySQL> getAllUnPackedMySQL();


    UnPackedMySQL getUnPackedMySQL(String name);

    // Vedere commento UnPackedDAOMySQL linea 141
    // FoodDAO updateFoodDAO(int averageExpiryDate);

    boolean updateUnPackedMySQL(String id, int averageExpiryDays);

    boolean deleteUnPackedMySQL(String id);

    boolean closeConnection();

}
