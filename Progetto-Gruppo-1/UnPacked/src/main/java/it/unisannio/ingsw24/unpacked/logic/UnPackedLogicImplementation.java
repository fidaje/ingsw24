package it.unisannio.ingsw24.unpacked.logic;

import java.util.Map;

import it.unisannio.ingsw24.unpacked.persistance.FoodDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAOMySQL;

public class UnPackedLogicImplementation implements UnPackedLogic{
    
    UnPackedDAO upd;

    public UnPackedLogicImplementation(){
        this.upd = new UnPackedDAOMySQL();
    }

    @Override
    public boolean createFoodDAO(String ID, String name, int averageExpiryDays, String category) {
        return this.upd.createFoodDAO(ID, name, averageExpiryDays, category);
    }

    @Override
    public FoodDAO getFoodDAO(String name) {
        return this.upd.getFoodDAO(name);
    }

    @Override
    public Map<String, FoodDAO> getAllFoodDAO() {
        return this.upd.getAllFoodDAO();
    }

    @Override
    public boolean updateFoodDAO(String ID, int averageExpiryDays) {
        return this.upd.updateFoodDAO(ID, averageExpiryDays);
    }

    @Override
    public boolean deleteFoodDAO(String ID) {
        return this.upd.deleteFoodDAO(ID);
    }

}
