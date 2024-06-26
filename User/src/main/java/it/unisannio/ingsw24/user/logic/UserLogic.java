package it.unisannio.ingsw24.user.logic;

import it.unisannio.ingsw24.entities.MyUser;
import java.util.ArrayList;

/**
 * This interface is used to define the methods that the UserLogic class must implement.
 */

public interface UserLogic {

    /**
     * This method is used to create a new user.
     * @param user The user to be created.
     * @return The username of the created user.
     */
    String createUser(MyUser user);

    /**
     * This method gets the user with the given username.
     * @param username The username of the user to be retrieved.
     * @return The user with the given username.
     */
    MyUser getUser(String username);

    /**
     * This method returns all the users in the database.
     * @return All the users in the database.
     */
    ArrayList<MyUser> getAllUsers();

    /**
     * This method deletes the user with the given username.
     * @param username The username of the user to be deleted.
     * @return True if the user was deleted, false otherwise.
     */
    boolean deleteUser(String username);

    /**
     * This method returns the next id to be assigned to a user.
     * @return The next id to be assigned to a user.
     */
    int getNextId();
}
