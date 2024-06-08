package it.unisannio.ingsw24.user;

import it.unisannio.ingsw24.entities.MyUser;
import it.unisannio.ingsw24.user.persistence.UserDAO;
import it.unisannio.ingsw24.user.persistence.UserDAOMongo;
import org.bson.Document;

public class TestID {

    public static void main(String[] args){

        UserDAO d = new UserDAOMongo();
        MyUser u = d.getUser("Tano");
        System.out.println(u);
    }
}
