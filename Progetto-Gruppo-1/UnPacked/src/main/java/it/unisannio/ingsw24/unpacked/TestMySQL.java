package it.unisannio.ingsw24.unpacked;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAOMySQL;


public class TestMySQL {
    public static void main(String[] args){
        UnPackedDAO upd = new UnPackedDAOMySQL();

        UnPackedFood voto = upd.getUnPackedFood("mela");

        System.out.println(voto);

    }
}
