package it.unisannio.ingsw24.unpacked.logic;

import java.util.Map;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.entities.UnPackedMySQL;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAOMySQL;

public class UnPackedLogicImplementation implements UnPackedLogic{
    
    UnPackedDAO upd;

    public UnPackedLogicImplementation(){
        this.upd = new UnPackedDAOMySQL();
    }

    @Override
    public int createUnPackedMySQL(String name, int averageExpiryDays, String category) {
        return this.upd.createUnPackedMySQL(name, averageExpiryDays, category);
    }

    @Override
    public UnPackedFood getUnPackedFood(String name) {
        UnPackedMySQL upms = this.upd.getUnPackedMySQL(name);
        return new UnPackedFood(upms.getName(), upms.getID(), false, false, 1, upms.getCategory(), Integer.toString(upms.getAverageExipireDays()));
    }

    @Override
    public Map<String, UnPackedMySQL> getAllUnPackedMySQL() {
        return this.upd.getAllUnPackedMySQL();
    }

    @Override
    public boolean updateUnPackedMySQL(int ID, int averageExpiryDays) {
        return this.upd.updateUnPackedMySQL(ID, averageExpiryDays);
    }

    @Override
    public boolean deleteUnPackedMySQL(int ID) {
        return this.upd.deleteUnPackedMySQL(ID);
    }

}
