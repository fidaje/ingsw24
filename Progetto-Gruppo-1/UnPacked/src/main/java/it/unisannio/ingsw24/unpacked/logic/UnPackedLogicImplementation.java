package it.unisannio.ingsw24.unpacked.logic;

import java.util.Map;

import it.unisannio.ingsw24.unpacked.persistance.UnPackedMySQL;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAOMySQL;

public class UnPackedLogicImplementation implements UnPackedLogic{
    
    UnPackedDAO upd;

    public UnPackedLogicImplementation(){
        this.upd = new UnPackedDAOMySQL();
    }

    @Override
    public boolean createFoodDAO(String ID, String name, int averageExpiryDays, String category) {
        return this.upd.createUnPackedMySQL(ID, name, averageExpiryDays, category);
    }

    @Override
    public UnPackedMySQL getFoodDAO(String name) {
        return this.upd.getUnPackedMySQL(name);
    }

    @Override
    public Map<String, UnPackedMySQL> getAllFoodDAO() {
        return this.upd.getAllUnPackedMySQL();
    }

    @Override
    public boolean updateFoodDAO(String ID, int averageExpiryDays) {
        return this.upd.updateUnPackedMySQL(ID, averageExpiryDays);
    }

    @Override
    public boolean deleteFoodDAO(String ID) {
        return this.upd.deleteUnPackedMySQL(ID);
    }

}
