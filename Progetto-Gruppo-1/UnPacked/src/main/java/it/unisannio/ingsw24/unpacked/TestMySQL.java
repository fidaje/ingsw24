package it.unisannio.ingsw24.unpacked;

import it.unisannio.ingsw24.unpacked.persistance.UnPackedMySQL;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAOMySQL;



public class TestMySQL {
    public static void main(String[] args){
        UnPackedDAO upd = new UnPackedDAOMySQL();

        UnPackedMySQL voto = upd.getUnPackedMySQL("mela");

        //List<String> names = upd.getAllUnPackedFoodNames();

        // System.out.println(names.size());

        System.out.println(voto);

        /*
         
         
        Map<String, UnPackedFood> mappa = upd.getAllUnPackedFood();

        Set<String> chiavi = mappa.keySet();

        for(String chiave : chiavi){
             UnPackedFood up = mappa.get(chiave);
             System.out.println(up + "\n");
            }
        System.out.println(chiavi.size());


        //boolean esito = upd.createUnPackedFood("ZZZZ", "francesco", 123456, "Others");
        //System.out.println(esito);
        boolean esito = upd.updateFoodDAO("ZZZZ", 34);
        System.out.println(esito);
        */
    }
}
