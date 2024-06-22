package it.unisannio.ingsw24.pantry;

import it.unisannio.ingsw24.pantry.*;
import it.unisannio.ingsw24.pantry.logic.PantryLogic;
import it.unisannio.ingsw24.pantry.logic.PantryLogicImplementation;
import org.junit.jupiter.api.*;
import it.unisannio.ingsw24.entities.*;
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
            System.out.println(pantryId);
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
        assertNull(p, "When making the get request with a non-existing id, you should not correctly obtain the pantry object");
    }

    @Test
    @Order(5)
    void updateFoods(){
        UnPackedFood unp = new UnPackedFood("fiorentina di SM", -12, false, false, 1, Category.OTHERS, "30");
        boolean result = pantryLogic.updateFoods(pantryId, unp);
        assertTrue(result);
    }

    @Test
    @Order(6)
    void updateGuests(){
        boolean result = pantryLogic.updateGuests(pantryId, "francescoiuorio8@gmail.com");
        assertTrue(result);
    }

    @Test
    @Order(7)
    void getFoods(){
        List<Food> foods = pantryLogic.getFoods(2);
        assertFalse(foods.isEmpty());
    }

    @Test
    @Order(8)
    void getFoodsAbsentPantry(){
        List<Food> foods = pantryLogic.getFoods(pantryLogic.getNextId());
        assertTrue(foods.isEmpty());
    }

    @Test
    @Order(9)
    void getFoodByName(){
        String foodName = "Gold Bunny";
        Food f = pantryLogic.getFoodByName(1, foodName);
        assertEquals(foodName, f.getName());
    }

    @Test
    @Order(10)
    void deleteFoodByName(){
        boolean result = pantryLogic.deleteFoodByName(pantryId,"fiorentina di SM");
        assertTrue(result);
    }

    @Test
    @Order(11)
    void deleteGuestByUsername(){
        boolean result = pantryLogic.deleteGuestByUsername(pantryId, "francescoiuorio8@gmail.com");
        assertTrue(result);
    }

    @Test
    @Order(12)
    void checkUsername(){
        boolean result = pantryLogic.checkUsername(pantryId, "francescoiuorio8@gmail.com");
        assertFalse(result);
    }


    @Test
    @Order(13)
    void checkOwner(){
        boolean result = pantryLogic.checkOwner(pantryId, "olivagaetano302@gmail.com");
        assertTrue(result);
    }


    @Test
    @Order(14)
    void getExpiredFoods(){
        List<Food> foods = pantryLogic.getExpiredFoods(pantryId);
        assertTrue(foods.isEmpty());
    }

    @Test
    @Order(14)
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
