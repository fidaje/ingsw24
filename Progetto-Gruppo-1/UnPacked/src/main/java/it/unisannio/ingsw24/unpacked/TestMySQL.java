package it.unisannio.ingsw24.unpacked;

import it.unisannio.ingsw24.unpacked.persistance.UnPackedMySQL;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAO;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedDAOMySQL;

import java.sql.SQLException;
import java.util.List;


public class TestMySQL {
    public static void main(String[] args) throws SQLException {
        UnPackedDAO upd = new UnPackedDAOMySQL();

       // int id = upd.createUnPackedMySQL("gaetano", 123343, "OTHERS");
        //System.out.println(id);

        System.out.println(upd.deleteUnPackedMySQL(2272));




        //UnPackedMySQL voto = upd.getUnPackedMySQL("mela");

        //List<String> names = upd.getAllUnPackedFoodNames();

        // System.out.println(names.size());

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
