package it.unisannio.ingsw24.pantry;

import it.unisannio.ingsw24.pantry.logic.PantryLogic;
import it.unisannio.ingsw24.pantry.logic.PantryLogicImplementation;
import org.junit.jupiter.api.*;
import it.unisannio.ingsw24.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PantryApplicationTests {
    static boolean init = false;
    static int pantryId;
    static PantryLogic pantryLogic;

    @BeforeAll
    static void setup(){
        if (!init){
            pantryLogic = new PantryLogicImplementation();
            pantryId = pantryLogic.getNextId();
            Pantry p = new Pantry("olivagaetano302@gmail.com");
            int pantry = pantryLogic.createPantry(p);
            assertEquals(pantryId, pantry);
            init = true;
        }
    }

    @Test
    @Order(1)
    void getPantries(){
        List<Pantry> pentries = pantryLogic.getPantries("olivagaetano302@gmail.com");
        assertFalse(pentries.isEmpty());
    }

    @Test
    void getPantriesInvalidUsername(){
        List<Pantry> pantries = pantryLogic.getPantries("notvalidusername@protonmail.com");
        assertTrue(pantries.isEmpty());
    }
    @Test
    @Order(2)
    void createPantry(){
        Pantry p = new Pantry("sprinstream@gmail.com");
        assertEquals(0, p.getId(), "When you create a pantry with an username that is not in the database, you should get 0");
    }

    @Test
    @Order(3)
    void getPantry(){
        Pantry p = pantryLogic.getPantry(pantryId);
        assertNotNull(p, "When making the get request with an existing id you should correctly obtain the pantry object");
    }

    @Test
    @Order(4)
    void getAbsentPantry(){
        int shouldNotExistId = pantryLogic.getNextId();
        Pantry p = pantryLogic.getPantry(shouldNotExistId);
        assertNull(p, "When making the get request with a non-existing id, you should not obtain the pantry object");
    }

    @Test
    @Order(5)
    void updateFoods(){
        UnPackedFood unp = new UnPackedFood("fiorentina di SM", 121212, false, false, 1, Category.OTHERS, "30");
        boolean result = pantryLogic.updateFoods(pantryId, unp);
        assertTrue(result);
    }

    @Test
    void updateFoodsInvalidPantryID(){
        UnPackedFood unp = new UnPackedFood("fiorentina di SM", 121212, false, false, 1, Category.OTHERS, "30");
        int notValidID =  pantryLogic.getNextId() + 1;
        boolean result = pantryLogic.updateFoods(notValidID, unp);
        assertFalse(result);
    }

    @Test
    @Order(6)
    void updateGuests(){
        boolean result = pantryLogic.updateGuests(pantryId, "francescoiuorio8@gmail.com");
        assertTrue(result);
    }

    @Test
    @Order(7)
    void addExistingGuest(){
        boolean result = pantryLogic.updateGuests(pantryId, "francescoiuorio8@gmail.com");
        assertFalse(result);
    }

    @Test
    @Order(16)
    void updateGuestInvalidUsername(){
        boolean result = pantryLogic.updateGuests(pantryId -1, "voto@gmail.com");
        assertFalse(result);
    }

    @Test
    @Order(8)
    void getFoods(){
        List<Food> foods = pantryLogic.getFoods(2);
        assertFalse(foods.isEmpty());
    }

    @Test
    @Order(9)
    void getFoodsAbsentPantry(){
        List<Food> foods = pantryLogic.getFoods(pantryLogic.getNextId());
        assertTrue(foods.isEmpty());
    }

    @Test
    @Order(10)
    void getFoodByName(){
        String foodName = "Gold Bunny";
        Food f = pantryLogic.getFoodByName(1, foodName);
        assertEquals(foodName, f.getName());
    }

    @Test
    void getFoodByWrongName(){
        String foodName = "Gold";
        Food f = pantryLogic.getFoodByName(1, foodName);
        assertNull(f);
    }


    // Attualmente funziona perché il metodo GetFoods restituisce una lista vuota
    // Da vedere se si vuole implementare una eccezione per id pantry non valido
    @Test
    void getFoodByNameWrongPantryID(){
        String foodname = "Gold Bunny";
        Food f = pantryLogic.getFoodByName(-1, foodname);
        assertNull(f);
    }

    @Test
    @Order(11)
    void deleteFoodByName(){
        boolean result = pantryLogic.deleteFoodByName(pantryId,"fiorentina di SM");
        assertTrue(result);
    }

    @Test
    @Order(17)
    void deleteFoodByWrongName(){
        boolean result = pantryLogic.deleteFoodByName(pantryId, "cibo");
        assertFalse(result);
    }

    @Test
    void deleteFoodByNameWrongPantryID(){
        boolean result = pantryLogic.deleteFoodByName(-1, "cibo");
        assertFalse(result);
    }

    @Test
    @Order(12)
    void deleteGuestByUsername(){
        boolean result = pantryLogic.deleteGuestByUsername(pantryId, "francescoiuorio8@gmail.com");
        assertTrue(result);
    }

    @Test
    void deleteGuestByOwnerUsername(){
        boolean result = pantryLogic.deleteGuestByUsername(1, "francescoiuorio8@gmail.com");
        assertFalse(result);
    }

    @Test
    void deleteGuestByUsernameWrongPantryID(){
        boolean result = pantryLogic.deleteGuestByUsername(-1, "francescoiuorio8@gmail.com");
        assertFalse(result);
    }

    @Test
    @Order(13)
    void checkUsername(){
        boolean result = pantryLogic.checkUsername(pantryId, "francescoiuorio8@gmail.com");
        assertFalse(result);
    }

    @Test
    @Order(14)
    void checkOwner(){
        boolean result = pantryLogic.checkOwner(pantryId, "olivagaetano302@gmail.com");
        assertTrue(result);
    }

    @Test
    @Order(15)
    void getExpiredFoods(){
        List<Food> foods = pantryLogic.getExpiredFoods(pantryId);
        assertTrue(foods.isEmpty());
    }

    @Test
    @Order(18)
    void deletePantry(){
        boolean result = pantryLogic.deletePantry(pantryId);
        assertTrue(result);
    }

    @Test
    void checkAndSetExpiredFoods(){
        int rows = pantryLogic.checkAndSetIsExpiredFoods();
        assertTrue(rows > 0);
    }

}
