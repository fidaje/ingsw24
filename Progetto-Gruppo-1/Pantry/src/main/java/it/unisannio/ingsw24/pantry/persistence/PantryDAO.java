package it.unisannio.ingsw24.pantry.persistence;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.Pantry;

import java.util.List;

public interface PantryDAO {

    String DATABASE_NAME = "CUSAS";

    String COLLECTION = "Pantries";

    String PANTRY_ID = "_id";

    String OWNER_ID = "OwnerID";

    String FOODS = "Foods";

    String GUESTS = "Guests";

    boolean dropDB();

    boolean createDB();

    boolean closeConnection();

    Long createPantry(Pantry p);

    List<Pantry> getPantries(int id);

    boolean updateFoods(Food f);

    boolean updateGuests(int idGuest);

    boolean deleteFoodByName(String name);

    boolean deleteGuestByID(int id);

    boolean deletePantry(int id);



}