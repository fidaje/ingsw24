package it.unisannio.ingsw24.user;


import it.unisannio.ingsw24.entities.MyUser;
import it.unisannio.ingsw24.user.logic.UserLogic;
import it.unisannio.ingsw24.user.logic.UserLogicImpl;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserLogicImplementationTest {

    static boolean init = false;
    static int userID;
    static UserLogic userLogic;


    @BeforeAll
    static void setup(){
        if (!init){
            userLogic = new UserLogicImpl();
            userID = userLogic.getNextId();
            assertTrue(userID>0);

            ArrayList<String> roles = new ArrayList<>();
            roles.add("OWNER");
            roles.add("GUEST");
            MyUser testUser = new MyUser(userID, "voto", "tobey", roles);
            String usernameTest = userLogic.createUser(testUser);

            assertNotNull(usernameTest);
            init = true;
        }
    }


    @Test
    void createUser(){
        ArrayList<String> roles = new ArrayList<>();
        roles.add("OWNER");
        roles.add("GUEST");
        MyUser testUser = new MyUser(userID, "voto", "tobey", roles);
        String usernameTest = userLogic.createUser(testUser);
        assertNotNull(usernameTest);
    }

    @Test
    @Order(1)
    void deleteUser(){
        boolean statement = userLogic.deleteUser("voto");
        assertTrue(statement);
    }

    @Test
    void getUser(){
        MyUser user = userLogic.getUser("olivagaetano302@gmail.com");
        assertNotNull(user);
    }
}
