package it.unisannio.ingsw24.entities;

import java.util.ArrayList;

/**
 * This class represents a user of the system.
 */
public class MyUser {

    private int id;
    private String username;
    private String password;
    private ArrayList<String> roles;

    /**
     * Constructor of the class.
     * @param id The id of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param roles The roles of the user.
     */
    public MyUser(int id, String username, String password, ArrayList<String> roles){
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Empty constructor of the class.
     */
    public MyUser(){}

    /**
     * Getter of the id.
     * @return The id of the user.
     */
    public int getId(){
        return this.id;
    }

    /**
     * Getter of the username.
     * @return The username of the user.
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Getter of the roles.
     * @return The roles of the user.
     */
    public ArrayList<String> getRoles(){
        return this.roles;
    }

    /**
     * Getter of the password.
     * @return The password of the user.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Setter of the id.
     * @param id The new id of the user.
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Setter of the password.
     * @param nPass The new password of the user.
     */
    public void setPassword(String nPass){
        this.password = nPass;
    }

    /**
     * Setter of the roles.
     * @param roles The new roles of the user.
     */
    public void setRoles(ArrayList<String> roles){
        this.roles = roles;
    }

    /**
     * Setter of the username.
     * @param username The new username of the user.
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * This method returns a string representation of the object.
     * @return The string representation of the object.
     */
    public String toString(){
        return "_id: " + this.id + " Username: " + this.username + " Roles: " + this.roles;
    }
}
