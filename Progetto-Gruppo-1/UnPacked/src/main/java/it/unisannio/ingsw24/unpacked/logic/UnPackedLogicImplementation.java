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
    public boolean createUnPackedMySQL(String ID, String name, int averageExpiryDays, String category) {
        return this.upd.createUnPackedMySQL(ID, name, averageExpiryDays, category);
    }

    @Override
    public UnPackedMySQL getUnPackedMySQL(String name) {
        return this.upd.getUnPackedMySQL(name);
    }

    @Override
    public Map<String, UnPackedMySQL> getAllUnPackedMySQL() {
        return this.upd.getAllUnPackedMySQL();
    }

    @Override
    public boolean updateUnPackedMySQL(String ID, int averageExpiryDays) {
        return this.upd.updateUnPackedMySQL(ID, averageExpiryDays);
    }

    @Override
    public boolean deleteUnPackedMySQL(String ID) {
        return this.upd.deleteUnPackedMySQL(ID);
    }

}
