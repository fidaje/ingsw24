package it.unisannio.ingsw24.gateway.logic;

import it.unisannio.ingsw24.entities.*;

import java.util.List;


public interface GatewayLogic {

    /**
     * This method creates a new user
     * @param user the user to be created
     * @return the username of the created user
     */
    String createUser(MyUser user);

    /**
     * This method creates a new pantry
     * @param pantry the pantry to be created
     * @return the id of the created pantry
     */
    int createPantry(Pantry pantry);

    /**
     * This method gets a user by username
     * @param username the username of the user
     * @return the user with the given username
     */
    MyUser getUser(String username);

    /**
     * This method gets a pantry by id
     * @param pantryId the id of the pantry
     * @return the pantry with the given id
     */
    Pantry getPantry(int pantryId);

    /**
     * This method gets the pantries of an owner
     * @param ownerUsername the username of the owner
     * @return the pantries of the owner
     */
    List<Pantry> getPantries(String ownerUsername);

    /**
     * This method gets the guests of a pantry
     * @param pantryId the id of the pantry
     * @return the guests of the pantry
     */
    List<Food> getFoods(int pantryId);

    /**
     * This method returns the food with the given name
     * @param pantryId the id of the pantry
     * @param name the name of the food
     * @return the food with the given name
     */
    Food getFoodFromPantryByName(int pantryId, String name);

    /**
     * This method returns the list of expired foods in the pantry
     * @param pantryId the id of the pantry
     * @return the list of expired foods in the pantry
     */
    List<Food> getExpiredFoods(int pantryId);

    /**
     * This method returns an unpacked food with the given name
     * @param name the name of the food
     * @return the unpacked food with the given name
     */
    UnPackedFood getUnPackedFood(String name);

    /**
     * This method returns a packed food with the given barcode
     * @param barcode the barcode of the food
     * @return the packed food with the given barcode
     */
    PackedFood getPackedFood(String barcode);

    /**
     * This method updates the password of a user
     * @param username the username of the user
     * @param password the new password of the user
     * @return true if the password is updated, false otherwise
     */
    boolean updatePassword(String username, String password);

    /**
     * This method updates the list of foods in a pantry with the given food
     * @param pantryId the id of the pantry
     * @param f the food to be added
     * @param type the Category of the food
     * @return 1 if the food is added, null otherwise
     */
    Integer updateFoods(int pantryId, Food f, String type);

    /**
     * This method updates the list of guests in a pantry with the given username
     * @param pantryId the id of the pantry
     * @param username the username of the guest that will be added
     * @return true if the guest is added, false otherwise
     */
    boolean updateGuests(int pantryId, String username);

    /**
     * This method deletes food from a pantry
     * @param pantryId the id of the pantry
     * @param foodName the name of the food to be deleted
     * @return true if the food is deleted, false otherwise
     */
    boolean deleteFoodByName(int pantryId, String foodName);

    /**
     * This method deletes a guest from a pantry using the username
     * @param pantryId the id of the pantry
     * @param username the username of the guest to be deleted
     * @return true if the guest is deleted, false otherwise
     */
    boolean deleteGuestByUsername(int pantryId, String username);

    /**
     * This method deletes a pantry
     * @param pantryId the id of the pantry to be deleted
     * @return true if the pantry is deleted, false otherwise
     */
    boolean deletePantry(int pantryId);

    /**
     * This method checks if the username is already used
     * @param pantryId the id of the pantry
     * @param username the username to be checked
     * @return true if the username is already used, false otherwise
     */
    boolean checkUsername(int pantryId, String username);

    /**
     * This method checks if the owner of the pantry is the same as the given username
     * @param pantryId the id of the pantry
     * @param ownerUsername the username to be checked
     * @return true if the owner of the pantry is the same as the given username, false otherwise
     */
    boolean checkOwner(int pantryId, String ownerUsername);
}
