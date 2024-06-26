package it.unisannio.ingsw24.user.persistence;

import it.unisannio.ingsw24.entities.MyUser;
import java.util.ArrayList;

/**
 * This interface is used to define the methods that a UserDAO class must implement.
 * The UserDAO class is used to interact with the database and perform operations on the Users collection.
 */
public interface UserDAO {

    String DATABASE_NAME = "CUTENTI";
    String COLLECTION = "Users";
    String ELEMENT_ID = "_id";
    String ELEMENT_USERNAME = "username";
    String ELEMENT_PASSWORD = "password";
    String ELEMENT_ROLES = "roles";

    int getNextId();
    boolean dropDB();
    boolean createDB();

    /**
     * This method is used to create a new user in the database.
     * @param user The user to be created.
     * @return The username of the user created.
     */
    String createUser(MyUser user);

    /**
     * This method is used to get a user from the database.
     * @param username
     * @return
     */
    MyUser getUser (String username);

    /**
     * This method is used to get all the users from the database.
     * @return An ArrayList of MyUser objects.
     */
    ArrayList<MyUser> getAllUsers();

    /**
     * This method is used to delete a user from the database.
     * @param username The username of the user to be deleted.
     * @return True if the user was deleted, false otherwise.
     */
    boolean killUser(String username);

    /**
     * This method closes the connection to the database.
     * @return True if the connection was closed, false otherwise.
     */
    boolean closeConnection();
}
