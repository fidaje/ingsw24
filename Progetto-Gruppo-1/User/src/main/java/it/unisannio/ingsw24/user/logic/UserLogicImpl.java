package it.unisannio.ingsw24.user.logic;

import it.unisannio.ingsw24.entities.MyUser;
import it.unisannio.ingsw24.user.persistence.*;

import java.util.ArrayList;

public class UserLogicImpl implements UserLogic{

    UserDAO dataManagerMongo;

    public UserLogicImpl(){
        dataManagerMongo = new UserDAOMongo();
    }

    @Override
    public String createUser(MyUser user) {
        return this.dataManagerMongo.createUser(user);
    }

    @Override
    public MyUser getUser(String username) {
        return this.dataManagerMongo.getUser(username);
    }

    @Override
    public ArrayList<MyUser> getAllUsers() {
        return dataManagerMongo.getAllUsers();
    }

    @Override
    public boolean deleteUser(String username) {
        return dataManagerMongo.killUser(username);
    }
}
