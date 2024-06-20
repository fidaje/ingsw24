package it.unisannio.ingsw24.pantry.persistence;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.Pantry;

import java.util.List;

public interface PantryDAO {

    String DATABASE_NAME = "CUSAS";

    String COLLECTION = "Pantries";

    String PANTRY_ID = "_id";

    String OWNER_USERNAME = "Owner_Username";

    String FOODS = "Foods";

    String GUESTS = "Guests";

    boolean dropDB();

    boolean createDB();

    boolean closeConnection();

    int createPantry(Pantry p);

    Pantry getPantry(int pantryId);

    List<Pantry> getPantries(String ownerUsername);

    boolean updateFoods(int pantryId, Food f);

    boolean updateGuests(int pantryId, String username);

    int checkAndSetIsExpiredFoods();

    List<Food> getFoods(int pantryId);

    Food getFoodByName(int pantryId, String name);

    List<Food> getExpiredFoods(int pantryId);

    boolean deleteFoodByName(int pantryId, String name);

    boolean deleteGuestByUsername(int pantryId, String username);

    boolean deletePantry(int id);

    boolean checkUsername(int pantryId, String username);

    boolean checkOwner(int pantryId, String ownerUsername);
}