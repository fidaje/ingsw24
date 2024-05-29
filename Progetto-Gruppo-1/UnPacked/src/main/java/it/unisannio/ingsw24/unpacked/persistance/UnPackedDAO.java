package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.UnPackedFood;

import java.util.List;
import java.util.Map;

public interface UnPackedDAO {

    String DATABASE_NAME = "UnPackedDB";

    String TABLE = "UNPACKEDFOOD";

    String ELEMENT_NAME = "Name";

    String ELEMENT_AVERAGE_EXPIRY_DATE = "Average_Expiry_Date";

    String ELEMENT_UNIQUE_ID = "Unique_ID";

    String ELEMENT_CATEGORY = "Category";

    boolean dropDB();

    boolean createUnPackedFood(String ID, String name, int average_exp_date, String category);

    Map<String, UnPackedFood> getAllUnPackedFood();

    List<String> getAllUnPackedFoodNames();

    UnPackedFood getUnPackedFood(String name);

    // Vedere commento UnPackedDAOMySQL linea 141
    // UnPackedFood updateUnPackedFood(int averageExpiryDate);

    boolean updateUnPackedFood(String id, int averageExpiryDate);

    boolean deleteUnPackedFood(String id);

    boolean closeConnection();

}
