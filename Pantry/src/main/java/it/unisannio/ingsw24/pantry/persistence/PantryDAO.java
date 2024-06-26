package it.unisannio.ingsw24.pantry.persistence;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.Pantry;
import java.util.List;


/**
 * This interfaces defines the methods that the DAO class must implement.
 * The DAO class is responsible for the communication between the application and the database.
 */
public interface PantryDAO {

    String DATABASE_NAME = "CUSAS";

    String COLLECTION = "Pantries";

    String PANTRY_ID = "_id";

    String OWNER_USERNAME = "Owner_Username";

    String FOODS = "Foods";

    String GUESTS = "Guests";

    /**
     * This method returns the id of the next Pantry object.
     * @return the id of the next Pantry object.
     */
    int getNextId();

    /**
     * This method drops the database.
     * @return true if the database is dropped, false otherwise.
     */
    boolean dropDB();

    /**
     * This method creates the database.
     * @return true if the database is created, false otherwise.
     */
    boolean createDB();

    /**
     * This method closes the connection to the database.
     * @return true if the connection is closed, false otherwise.
     */
    boolean closeConnection();

    /**
     * This method creates a Pantry object in the database.
     * @return the id of the created object.
     */
    int createPantry(Pantry p);

    /**
     * This method returns the Pantry object with the given id.
     * @param pantryId the id of the Pantry object.
     * @return the Pantry object with the given id.
     */
    Pantry getPantry(int pantryId);

    /**
     * This method returns a list of all the Pantry objects in the database.
     * @return a list of all the Pantry objects in the database.
     */
    List<Pantry> getPantries(String ownerUsername);

/**
     * This method updates the Pantry object with the given id, adding a new Food object.
     * @param pantryId the id of the Pantry object.
     * @param f the Food object to add.
     * @return true if the Pantry object is updated, false otherwise.
     */
    boolean updateFoods(int pantryId, Food f);

    /**
     * This method updates the Pantry object with the given id, adding a new guest.
     * @param pantryId the id of the Pantry object.
     * @param username the username of the guest to add.
     * @return true if the Pantry object is updated, false otherwise.
     */
    boolean updateGuests(int pantryId, String username);

    /**
     * This method checks if the Food objects in the Pantry object with the given id are expired and sets the isExpired attribute.
     * @return the number of expired Food objects.
     */
    int checkAndSetIsExpiredFoods();

    /**
     * This method sends an email to the owner of the Pantry object with the given id, containing the list of expired Food objects.
     */
    void sendMail();

    /**
     * This method returns a list of all the Food objects in the Pantry object with the given id.
     * @return a list of all the Food objects in the Pantry object with the given id.
     */
    List<Food> getFoods(int pantryId);

    /**
     * This method returns a Food object with the given name in the Pantry object with the given id.
     * @param pantryId
     * @param name
     * @return the Food object with the given name in the Pantry object with the given id.
     */
    Food getFoodByName(int pantryId, String name);

    /**
     * This method returns a list of foods that are expired in the Pantry object with the given id.
     * @param pantryId
     * @return a list of foods that are expired in the Pantry object with the given id.
     */
    List<Food> getExpiredFoods(int pantryId);

    /**
     * This method deletes the Food object with the given name in the Pantry object with the given id.
     * @param pantryId
     * @param name
     * @return true if the Food object is deleted, false otherwise.
     */
    boolean deleteFoodByName(int pantryId, String name);

    /**
     * This method deletes the guest with the given username in the Pantry object with the given id.
     * @param pantryId
     * @param username
     * @return true if the guest is deleted, false otherwise.
     */
    boolean deleteGuestByUsername(int pantryId, String username);

    /**
     * This method deletes the Pantry object with the given id.
     * @param id the id of the Pantry object.
     * @return true if the Pantry object is deleted, false otherwise.
     */
    boolean deletePantry(int id);

    /**
     * This method verifies if the username is already present in the Pantry object with the given id.
     * @param pantryId
     * @param username
     * @return true if the username is already present, false otherwise.
     */
    boolean checkUsername(int pantryId, String username);

    /**
     * This method verifies if the ownerUsername is the owner of the Pantry object with the given id.
     * @param pantryId
     * @param ownerUsername
     * @return true if the ownerUsername is the owner, false otherwise.
     */
    boolean checkOwner(int pantryId, String ownerUsername);
}