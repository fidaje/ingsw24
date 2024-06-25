package it.unisannio.ingsw24.packed;

import it.unisannio.ingsw24.entities.PackedFood;
import it.unisannio.ingsw24.packed.logic.PackedLogic;
import it.unisannio.ingsw24.packed.logic.PackedLogicImplemetation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PackedLogicImplementationTests {

    static boolean init = false;
    static PackedLogic packedLogic;
    static PackedFood pf;

    @BeforeAll
    public static void setup(){
        if(!init) {
            packedLogic = new PackedLogicImplemetation();
            init = true;
        }
    }


    @Test
    void getPackedFood() {
        PackedFood packedFood = packedLogic.getPackedFood("4000539671203");
        PackedFood pf = new PackedFood("Gold Bunny", "4000539671203", LocalDate.now(), false, false, 1, "Lindt", "e");
        assertEquals(pf.getBrand(), packedFood.getBrand());
    }

}
