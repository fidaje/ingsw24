package it.unisannio.ingsw24.unpacked.logic;

import java.util.Map;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAOMySQL;

public class UnPackedLogicImplementation implements UnPackedLogic{
    
    UnPackedDAO upd;

    public UnPackedLogicImplementation(){
        this.upd = new UnPackedDAOMySQL();
    }

    @Override
    public boolean createUnPackedFood(String ID, String name, int averageExpiryDays, String category) {
        return this.upd.createUnPackedFood(ID, name, averageExpiryDays, category);
    }

    @Override
    public UnPackedFood getUnPackedFood(String name) {
        return this.upd.getUnPackedFood(name);
    }

    @Override
    public Map<String, UnPackedFood> getAllUnPackedFood() {
        return this.upd.getAllUnPackedFood();
    }

    @Override
    public boolean updateUnPackedFood(String ID, int averageExpiryDays) {
        return this.upd.updateUnPackedFood(ID, averageExpiryDays);
    }

    @Override
    public boolean deleteUnPackedFood(String ID) {
        return this.upd.deleteUnPackedFood(ID);
    }

}
