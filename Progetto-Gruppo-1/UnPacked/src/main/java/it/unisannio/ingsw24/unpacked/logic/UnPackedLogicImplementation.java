package it.unisannio.ingsw24.unpacked.logic;

import java.util.List;

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
    public int getNextId(){
        return this.upd.getNextId();
    }

    @Override
    public int createUnPackedMySQL(String name, int averageExpiryDays, String category) {
        return this.upd.createUnPackedMySQL(name, averageExpiryDays, category);
    }

    @Override
    public UnPackedFood getUnPackedFood(String name) {
        UnPackedMySQL upms = this.upd.getUnPackedMySQL(name);
        if (upms == null)
            return null;
        return new UnPackedFood(upms.getName(), upms.getID(), false, false, 1, upms.getCategory(), Integer.toString(upms.getAverageExipireDays()));
    }

    @Override
    public List<String> getAllUnPackedMySQLNames() {
        return this.upd.getAllUnPackedMySQLNames();
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
