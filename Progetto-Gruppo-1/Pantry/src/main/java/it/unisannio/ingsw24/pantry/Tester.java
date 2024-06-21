package it.unisannio.ingsw24.pantry;

import it.unisannio.ingsw24.entities.*;
import it.unisannio.ingsw24.pantry.persistence.PantryDAOMongo;

import java.util.List;

public class Tester {

    public static void main(String[] args){

        PantryDAOMongo pantryDAO = new PantryDAOMongo();

        pantryDAO.sendMail();

    }
}
