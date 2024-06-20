package it.unisannio.ingsw24.pantry;

import it.unisannio.ingsw24.entities.*;
import it.unisannio.ingsw24.pantry.persistence.PantryDAOMongo;

import java.util.List;

public class Tester {

    public static void main(String[] args){

        PantryDAOMongo pantryDAO = new PantryDAOMongo();

        /*Food f = new UnPackedFood("Cibo",2,false, true,
                2, Category.BAKERY, "15");

        Food f1 = new PackedFood("Verstanpen", "8080", LocalDate.now(), false, false, 12, "Ferrero", "A");

        System.out.println(f);

        boolean b = pantryDAO.updateFoods(1, f1);*/

        // boolean b = pantryDAO.deleteFoodByName(1, "Cibo");
        //int id = pantryDAO.createPantry(new Pantry("tanucco"));

        // int row = pantryDAO.checkIsExpiredFoods();

        List<Food> fs = pantryDAO.getExpiredFoods(1);

        for(Food f : fs){
            System.out.println(f);
        }

        System.out.println(fs.size());



    }
}
