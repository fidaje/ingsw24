package it.unisannio.ingsw24.user.logic;

import it.unisannio.ingsw24.entities.MyUser;
import it.unisannio.ingsw24.user.persistence.*;
import java.util.ArrayList;

/**
 * This class implements the UserLogic interface and is used to manage the logic of the user.
 */
public class UserLogicImpl implements UserLogic{

    UserDAO dataManagerMongo;

    /**
     * This constructor initializes the dataManagerMongo attribute.
     */
    public UserLogicImpl(){
        dataManagerMongo = new UserDAOMongo();
    }

    /**
     * This method is used to create a new user.
     * @param user The user to be created.
     * @return The username of the created user.
     */
    @Override
    public String createUser(MyUser user) {
        return this.dataManagerMongo.createUser(user);
    }

    /**
     * This method gets the user with the given username.
     * @param username The username of the user to be retrieved.
     * @return The user with the given username.
     */
    @Override
    public MyUser getUser(String username) {
        return this.dataManagerMongo.getUser(username);
    }

    /**
     * This method returns all the users in the database.
     * @return All the users in the database.
     */
    @Override
    public ArrayList<MyUser> getAllUsers() {
        return dataManagerMongo.getAllUsers();
    }

    /**
     * This method deletes the user with the given username.
     * @param username The username of the user to be deleted.
     * @return True if the user was deleted, false otherwise.
     */
    @Override
    public boolean deleteUser(String username) {
        return dataManagerMongo.killUser(username);
    }

    /**
     * This method is used to change user's password
     * @param username is the user that want change password
     * @param password is the new password
     * @return True if the password was changed, False otherwise.
     */
    @Override
    public boolean updatePassword(String username, String password){
        return dataManagerMongo.updatePassword(username, password);
    }

    /**
     * This method returns the next id to be assigned to a user.
     * @return The next id to be assigned to a user.
     */
    @Override
    public int getNextId(){
        return this.dataManagerMongo.getNextId();
    }
}
