package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.UnPackedMySQL;
import java.util.List;

/**
 * This interfaces defines the methods that the DAO class must implement.
 * The DAO class is responsible for the communication between the application and the database.
 */

public interface UnPackedDAO {

    String DATABASE_NAME = "UnPackedDB";

    String TABLE = "UNPACKEDFOOD";

    String ELEMENT_NAME = "Name";

    String ELEMENT_AVERAGE_EXPIRY_DAYS = "Average_Expiry_Days";

    String ELEMENT_UNIQUE_ID = "Unique_ID";

    String ELEMENT_CATEGORY = "Category";

    /**
     * This method drops the database.
     * @return true if the database is dropped, false otherwise.
     */
    boolean dropDB();

    /**
     * This method creates an UnPackedMySQL object in the database.
     * @return the id of the created object.
     */
    int createUnPackedMySQL(String name, int averageExpiryDays, String category);

    /**
     * This method returns a list of all the UnPackedMySQL objects in the database.
     * @return a list of all the UnPackedMySQL objects in the database.
     */
    List<String> getAllUnPackedMySQLNames();

    /**
     * This method returns the id of the next UnPackedMySQL object.
     * @return the id of the next UnPackedMySQL object.
     */
    public int getNextId();

    /**
     * This method returns the UnPackedMySQL object with the given name.
     * @param name the id of the UnPackedMySQL object.
     * @return the UnPackedMySQL object with the given id.
     */
    UnPackedMySQL getUnPackedMySQL(String name);

    // Vedere commento UnPackedDAOMySQL linea 141
    // FoodDAO updateFoodDAO(int averageExpiryDate);

    /**
     * This method updates the UnPackedMySQL object with the given id.
     * @param id the id of the UnPackedMySQL object.
     * @param averageExpiryDays the new average expiry days of the UnPackedMySQL object.
     * @return true if the UnPackedMySQL object is updated, false otherwise.
     */
    boolean updateUnPackedMySQL(int id, int averageExpiryDays);

    /**
     * This method deletes the UnPackedMySQL object with the given id.
     * @param id the id of the UnPackedMySQL object.
     * @return true if the UnPackedMySQL object is deleted, false otherwise.
     */
    boolean deleteUnPackedMySQL(int id);

    /**
     * This method closes the connection with the database.
     * @return true if the connection is closed, false otherwise.
     */
    boolean closeConnection();
}
