package it.unisannio.ingsw24.user.logic;

import it.unisannio.ingsw24.entities.MyUser;

import java.util.ArrayList;

public interface UserLogic {

    String createUser(MyUser user);

    MyUser getUser(String username);

    ArrayList<MyUser> getAllUsers();

    boolean deleteUser(String username);

    int getNextId();



}
