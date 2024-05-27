package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.UnPackedFood;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UnPackedDAO {

    String DATABASE_NAME = "UnPackedDB";

    String TABLE = "UNPACKEDFOOD";

    String ELEMENT_NAME = "Name";

    String ELEMENT_AVERAGE_EXPIRY_DATE = "Average_Expiry_Date";

    String ELEMENT_UNIQUE_ID = "Unique_ID";

    String ELEMENT_CATEGORY = "Category";

    boolean dropDB();

    String createUnPackedFood(UnPackedFood UF);

    Map<String, UnPackedFood> getAllUnPackedFood();

    List<String> getAllUnPackedFoodNames();

    UnPackedFood getUnPackedFood(String name);

    UnPackedFood updateUnPackedFood(int averageExpiryDate);

    boolean deleteUnPackedFood(Long frameNumber);

    boolean closeConnection();

}
