package it.unisannio.ingsw24.pantry.logic;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.Pantry;

import java.util.List;

public interface PantryLogic {

    int createPantry(Pantry p);

    Pantry getPantry(int pantryId);

    List<Pantry> getPantries(String ownerUsername);

    boolean updateFoods(int pantryId, Food f);

    boolean updateGuests(int pantryId, String username);

    int checkAndSetIsExpiredFoods();

    List<Food> getFoods(int pantryId);

    List<Food> getExpiredFoods(int pantryId);

    boolean deleteFoodByName(int pantryId, String name);

    boolean deleteGuestByUsername(int pantryId, String username);

    boolean deletePantry(int id);



}