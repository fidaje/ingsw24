package it.unisannio.ingsw24.unpacked;

import java.util.List;
import java.util.Set;
import java.util.Map;

import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAOMySQL;



public class TestMySQL {
    public static void main(String[] args){
        UnPackedDAO upd = new UnPackedDAOMySQL();

        //UnPackedFood voto = upd.getUnPackedFood("mela");

        // List<String> names = upd.getAllUnPackedFoodNames();

        // System.out.println(names.size());

        //System.out.println(voto);

        /*
         
         
        Map<String, UnPackedFood> mappa = upd.getAllUnPackedFood();

        Set<String> chiavi = mappa.keySet();

        for(String chiave : chiavi){
             UnPackedFood up = mappa.get(chiave);
             System.out.println(up + "\n");
            }
        System.out.println(chiavi.size());
        */

        //boolean esito = upd.createUnPackedFood("ZZZZ", "francesco", 123456, "Others");
        //System.out.println(esito);
        boolean esito = upd.updateUnPackedFood("ZZZZ", 34);
        System.out.println(esito);
    }
}
