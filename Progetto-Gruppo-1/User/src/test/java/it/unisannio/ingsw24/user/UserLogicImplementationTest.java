package it.unisannio.ingsw24.user;

import it.unisannio.ingsw24.entities.MyUser;
import it.unisannio.ingsw24.user.logic.UserLogic;
import it.unisannio.ingsw24.user.logic.UserLogicImpl;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;

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
            MyUser testUser = new MyUser(userID, "sprintstrim@gmail.com", "tobey", roles);
            String usernameTest = userLogic.createUser(testUser);

            assertNotNull(usernameTest);
            init = true;
        }
    }

    @Test
    @Order(1)
    void createUser(){
        ArrayList<String> roles = new ArrayList<>();
        roles.add("OWNER");
        roles.add("GUEST");
        MyUser testUser = new MyUser(userID, "sprintstrim@gmail.com", "tobey", roles);
        String username = userLogic.createUser(testUser);
        assertNull(username);
    }

    @Test
    @Order(2)
    void deleteUser(){
        boolean statement = userLogic.deleteUser("sprintstrim@gmail.com");
        assertTrue(statement);
    }

    @Test
    @Order(3)
    void getUser(){
        MyUser user = userLogic.getUser("olivagaetano302@gmail.com");
        assertNotNull(user);
    }

    @Test
    @Order(4)
    void getAllUsers(){
        ArrayList<MyUser> users = userLogic.getAllUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    @Order(5)
    void getAbsentUser(){
        MyUser user = userLogic.getUser("sprintstrim@gmail.com");
        assertNull(user);
    }



}
