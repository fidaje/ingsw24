package it.unisannio.ingsw24.pantry;

import it.unisannio.ingsw24.pantry.*;
import it.unisannio.ingsw24.pantry.logic.PantryLogic;
import it.unisannio.ingsw24.pantry.logic.PantryLogicImplementation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import it.unisannio.ingsw24.entities.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PantryApplicationTests {

    static boolean init = false;
    static int pantryId;
    static PantryLogic pantryLogic;

    @BeforeAll
    static void setup(){
        if (!init){
            pantryLogic = new PantryLogicImplementation();
            pantryId = pantryLogic.getNextId();
            System.out.println(pantryId);
            Pantry p = new Pantry("olivagaetano302@gmail.com");
            int pantry = pantryLogic.createPantry(p);
            assertEquals(pantryId, pantry);
            init = true;
        }
    }

    @Test
    void createPantry(){
        assertTrue(true);
    }

}
