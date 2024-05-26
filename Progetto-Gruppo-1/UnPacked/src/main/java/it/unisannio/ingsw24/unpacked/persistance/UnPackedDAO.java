package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.UnPackedFood;
import java.util.Map;

public interface UnPackedDAO {

    String DATABASE_NAME = "PROVAWM";

    String TABLE_FRUIT = "FRUIT";

    String TABLE_VEGETABLES = "VEGETABLES";

    String ELEMENT_NAME = "Name";

    String ELEMENT_Average_Expiry_Date = "Average_Expiry_Date";

    String ELEMENT_UNIQUE_ID = "Unique_ID";

    boolean dropDB();

    String createUnPackedFood(UnPackedFood UF);

    Map<String, UnPackedFood> getAllUnPackedFood();

    UnPackedFood getUnPackedFood(String name);

    UnPackedFood updateUnPackedFood(int averageExpiryDate);

    boolean deleteUnPackedFood(Long frameNumber);

    boolean closeConnection();
}
