package it.unisannio.ingsw24.user;

import it.unisannio.ingsw24.entities.MyUser;
import it.unisannio.ingsw24.user.persistence.UserDAO;
import it.unisannio.ingsw24.user.persistence.UserDAOMongo;
import org.bson.Document;

import java.util.ArrayList;

public class TestID {

    public static void main(String[] args){

        UserDAO d = new UserDAOMongo();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("OWNER");
        d.createUser(new MyUser(2, "tanuccio", "squalo", roles));
        System.out.println("vediamo");
    }
}
