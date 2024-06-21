package it.unisannio.ingsw24.pantry.logic;

import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.Pantry;
import it.unisannio.ingsw24.pantry.persistence.PantryDAO;
import it.unisannio.ingsw24.pantry.persistence.PantryDAOMongo;

import java.util.List;

public class PantryLogicImplementation implements PantryLogic {

    PantryDAO pantryDataManager;

    public PantryLogicImplementation(){
        this.pantryDataManager = new PantryDAOMongo();
    }

    @Override
    public int createPantry(Pantry p) {
        return this.pantryDataManager.createPantry(p);
    }

    @Override
    public Pantry getPantry(int pantryId){
        return this.pantryDataManager.getPantry(pantryId);
    }

    @Override
    public List<Pantry> getPantries(String ownerUsername) {
        return this.pantryDataManager.getPantries(ownerUsername);
    }

    @Override
    public boolean updateFoods(int pantryId, Food f) {
        return this.pantryDataManager.updateFoods(pantryId, f);
    }

    @Override
    public boolean updateGuests(int pantryId, String username) {
        return this.pantryDataManager.updateGuests(pantryId, username);
    }

    @Override
    public int checkAndSetIsExpiredFoods(){
        return this.pantryDataManager.checkAndSetIsExpiredFoods();
    }

    @Override
    public void sendMail(){
        this.pantryDataManager.sendMail();
    }

    @Override
    public List<Food> getFoods(int pantryId){
        return this.pantryDataManager.getFoods(pantryId);
    }

    @Override
    public Food getFoodByName(int pantryId, String name){
        return this.pantryDataManager.getFoodByName(pantryId, name);
    }

    @Override
    public List<Food> getExpiredFoods(int pantryId){
        return this.pantryDataManager.getExpiredFoods(pantryId);
    }

    @Override
    public boolean deleteFoodByName(int pantryId, String name) {
        return this.pantryDataManager.deleteFoodByName(pantryId, name);
    }

    @Override
    public boolean deleteGuestByUsername(int pantryId, String username) {
        return this.pantryDataManager.deleteGuestByUsername(pantryId, username);
    }

    @Override
    public boolean deletePantry(int id) {
        return this.pantryDataManager.deletePantry(id);
    }

    @Override
    public boolean checkUsername(int pantryId, String username){
        return this.pantryDataManager.checkUsername(pantryId, username);
    }

    @Override
    public int getNextId(){
        return this.pantryDataManager.getNextId();
    }

    @Override
    public boolean checkOwner(int pantryId, String ownerUsername){
        return this.pantryDataManager.checkOwner(pantryId, ownerUsername);
    }
}