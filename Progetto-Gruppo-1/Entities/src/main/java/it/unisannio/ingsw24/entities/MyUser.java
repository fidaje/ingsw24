package it.unisannio.ingsw24.entities;

import java.util.ArrayList;

public class MyUser {

    private String username;
    private String password;
    private ArrayList<String> roles;

    public MyUser(String username, String password, ArrayList<String> roles){
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public MyUser(){}

    public String getUsername(){
        return this.username;
    }

    public ArrayList<String> getRoles(){
        return this.roles;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String nPass){
        this.password = nPass;
    }

    public void setRoles(ArrayList<String> roles){
        this.roles = roles;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String toString(){
        return "Username: " + this.username + " Roles: " + this.roles;
    }
}
