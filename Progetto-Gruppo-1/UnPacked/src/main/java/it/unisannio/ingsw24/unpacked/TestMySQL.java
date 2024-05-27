package it.unisannio.ingsw24.unpacked;

import java.util.List;
import java.util.Set;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAOMySQL;



public class TestMySQL {
    public static void main(String[] args){
        UnPackedDAO upd = new UnPackedDAOMySQL();

        UnPackedFood voto = upd.getUnPackedFood("miele");

        List<String> names = upd.getAllUnPackedFoodNames();

        System.out.println(names.size());

        System.out.println(voto);

    }
}
