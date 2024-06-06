package it.unisannio.ingsw24.pantry;

import it.unisannio.ingsw24.entities.Pantry;
import it.unisannio.ingsw24.pantry.persistence.PantryDAO;
import it.unisannio.ingsw24.pantry.persistence.PantryDAOMongo;

public class Tester {

    public static void main(String[] args){

        PantryDAOMongo pantryDAO = new PantryDAOMongo();

        // Pantry p = pantryDAO.getPantry(2);

        Pantry p = new Pantry(3);

        pantryDAO.createPantry(p);

        System.out.println(p);

    }
}
