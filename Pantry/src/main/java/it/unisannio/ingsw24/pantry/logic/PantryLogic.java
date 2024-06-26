package it.unisannio.ingsw24.pantry.logic;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.Pantry;
import java.util.List;

/**
 * The PantryLogic interface defines the methods that the PantryLogicImpl class
 * must implement
 */
public interface PantryLogic {

    /**
     * Returns the next available pantry ID
     * @return the next available pantry ID
     */
    int getNextId();

    /**
     * Creates a new pantry
     * @param p the pantry to create
     * @return the ID of the created pantry
     */
    int createPantry(Pantry p);

    /**
     * Returns the pantry with the specified ID
     * @param pantryId the ID of the pantry to return
     * @return the pantry with the specified ID
     */
    Pantry getPantry(int pantryId);

    /**
     * Returns the pantries owned by the specified user
     * @param ownerUsername the username of the owner
     * @return the pantries owned by the specified user
     */
    List<Pantry> getPantries(String ownerUsername);

    /**
     * Adds a food to the specified pantry
     * @param pantryId the ID of the pantry
     * @param f the food to add
     * @return true if the food was added, false otherwise
     */
    boolean updateFoods(int pantryId, Food f);

    /**
     * Adds a guest to the specified pantry
     * @param pantryId the ID of the pantry
     * @param username the username of the guest
     * @return true if the guest was added, false otherwise
     */
    boolean updateGuests(int pantryId, String username);

    /**
     * Checks and sets the isExpired flag of the expired foods
     * @return the number of expired foods
     */
    int checkAndSetIsExpiredFoods();

    /**
     * Sends an email to the owner of the expired foods
     */
    void sendMail();

    /**
     * Returns the foods in the specified pantry
     * @param pantryId the ID of the pantry
     * @return the foods in the specified pantry
     */
    List<Food> getFoods(int pantryId);

    /**
     * Returns the food with the specified name in the specified pantry
     * @param pantryId the ID of the pantry
     * @param name the name of the food
     * @return the food with the specified name in the specified pantry
     */
    Food getFoodByName(int pantryId, String name);

    /**
     * *Returns the expired foods of the specified pantry
     * @param pantryId the ID of the pantry
     * @return the expired foods of the specified pantry
     */
    List<Food> getExpiredFoods(int pantryId);

    /**
     * Deletes the food with the specified name in the specified pantry
     * @param pantryId the ID of the pantry
     * @param name the name of the food
     * @return true if the food was deleted, false otherwise
     */
    boolean deleteFoodByName(int pantryId, String name);

    /**
     * Deletes the guest with the specified username in the specified pantry
     * @param pantryId the ID of the pantry
     * @param username the username of the guest
     * @return true if the guest was deleted, false otherwise
     */
    boolean deleteGuestByUsername(int pantryId, String username);

    /**
     * Deletes the specified pantry
     * @param id the ID of the pantry
     * @return true if the pantry was deleted, false otherwise
     */
    boolean deletePantry(int id);

    /**
     * Checks if the specified username is present in the specified pantry
     * @param pantryId the ID of the pantry
     * @param username the username to check
     * @return true if the specified username is present in the specified pantry, false otherwise
     */
    boolean checkUsername(int pantryId, String username);

    /**
     * Checks if the specified owner is the owner of the specified pantry
     * @param pantryId the ID of the pantry
     * @param ownerUsername the owner to check
     * @return true if the specified owner is the owner of the specified pantry, false otherwise
     */
    boolean checkOwner(int pantryId, String ownerUsername);
}