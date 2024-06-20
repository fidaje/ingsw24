package it.unisannio.ingsw24.entities;

import java.util.ArrayList;

public class MyUser {

    private int id;
    private String username;
    private String password;
    private ArrayList<String> roles;

    public MyUser(int id, String username, String password, ArrayList<String> roles){
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public MyUser(){}

    public int getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public ArrayList<String> getRoles(){
        return this.roles;
    }

    public String getPassword(){
        return this.password;
    }

    public void setId(int id){
        this.id = id;
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
        return "_id: " + this.id + " Username: " + this.username + " Roles: " + this.roles;
    }
}
