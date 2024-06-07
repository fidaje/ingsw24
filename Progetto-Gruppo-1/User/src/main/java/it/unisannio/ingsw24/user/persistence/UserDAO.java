package it.unisannio.ingsw24.user.persistence;

import it.unisannio.ingsw24.entities.MyUser;

import java.util.ArrayList;

public interface UserDAO {

    String DATABASE_NAME = "CUTENTI";
    String COLLECTION = "Users";
    String ELEMENT_USERNAME = "username";
    String ELEMENT_PASSWORD = "password";
    String ELEMENT_ROLES = "roles";

    boolean dropDB();
    boolean createDB();

    String createUser(MyUser user);
    MyUser getUser (String username);
    ArrayList<MyUser> getAllUsers();

    boolean killUser(String username);
    boolean closeConnection();
}
