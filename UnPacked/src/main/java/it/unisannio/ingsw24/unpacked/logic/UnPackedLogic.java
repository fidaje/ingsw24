package it.unisannio.ingsw24.unpacked.logic;

import it.unisannio.ingsw24.entities.UnPackedFood;
import java.util.List;

/**
 * Interface for the logic of the UnPackedFood entity.
 */
public interface UnPackedLogic {

    /**
     * Get the next ID for the UnPackedFood entity.
     * @return the next ID for the UnPackedFood entity.
     */
    int getNextId();

    /**
     * Create a new UnPackedFood entity.
     * @param name the name of the UnPackedFood entity.
     * @param averageExpiryDays the average expiry days of the UnPackedFood entity.
     * @param category the category of the UnPackedFood entity.
     * @return the ID of the new UnPackedFood entity.
     */
    int createUnPackedMySQL(String name, int averageExpiryDays, String category);

    /**
     * Get the UnPackedFood entity with the specified name.
     * @param name the name of the UnPackedFood entity.
     * @return the UnPackedFood entity with the specified name.
     */
    UnPackedFood getUnPackedFood(String name);

    /**
     * Get all the UnPackedFood entities from the database.
     * @return all the UnPackedFood entities from the database.
     */
    List<String> getAllUnPackedMySQLNames();

    /**
     * This method updates the UnPackedFood entity with the specified ID whit the specified average expiry days.
     * @param ID
     * @param averageExpiryDays
     * @return true if the update was successful, false otherwise.
     */
    boolean updateUnPackedMySQL(int ID, int averageExpiryDays);

    /**
     * This method deletes the UnPackedFood entity with the specified ID.
     * @param ID
     * @return true if the deletion was successful, false otherwise.
     */
    boolean deleteUnPackedMySQL(int ID);

}
