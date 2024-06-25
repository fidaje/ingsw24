package it.unisannio.ingsw24.unpacked;

import it.unisannio.ingsw24.entities.Category;
import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.entities.UnPackedMySQL;
import it.unisannio.ingsw24.unpacked.logic.UnPackedLogic;
import it.unisannio.ingsw24.unpacked.logic.UnPackedLogicImplementation;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UnPackedLogicImplementationTest {

    static boolean init = false;
    static UnPackedLogic unPackedLogic;
    static int up1Id;
    static UnPackedMySQL up1;
    static UnPackedFood upf1;

    @BeforeAll
    static void setup(){
        if(!init) {
            unPackedLogic = new UnPackedLogicImplementation();
            up1Id = unPackedLogic.getNextId();
            assertTrue(up1Id > 0);
            int unPacked = unPackedLogic.createUnPackedMySQL("fiorentina di SM", 30, "OTHERS");
            assertEquals(up1Id, unPacked);
            upf1 = new UnPackedFood("fiorentina di SM", up1Id, false, false, 1, Category.OTHERS, "30");
            init = true;
        }
    }

    @Test
    @Order(1)
    void createUnPackedMySQL(){
        assertEquals(0, unPackedLogic.createUnPackedMySQL("fiorentina di SM", 30, "OTHERS"));
    }

    @Test
    @Order(2)
    void getUnPackedFood() {
        UnPackedFood upf = unPackedLogic.getUnPackedFood("fiorentina di SM");
        assertEquals(upf1, upf);
    }

    @Test
    @Order(3)
    void updateUnPackedMySQL(){
        assertTrue(unPackedLogic.updateUnPackedMySQL(up1Id, 4));
    }

    @Test
    @Order(4)
    void deleteUnPackedMySQL(){
        assertTrue(unPackedLogic.deleteUnPackedMySQL(up1Id));
    }

    @Test
    void getAllUnPackedMySQLNames() {
        List<String> allNames = unPackedLogic.getAllUnPackedMySQLNames();
        assertFalse(allNames.isEmpty());
    }

    @Test
    void getAbsentUnPackedFood(){
        assertNull(unPackedLogic.getUnPackedFood("foodScanner"));
    }


}
