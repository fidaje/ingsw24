package it.unisannio.ingsw24.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a pantry, which is a collection of foods owned by a user.
 * The pantry has an owner, a list of foods and a list of guests.
 */

public class Pantry {

    private int id;
    private List<Food> fuds;
    private String ownerUsername;
    private List<String> guestsUsernames;

    /**
     * Default constructor
     */
    public Pantry(){
    }

    /**
     * Constructor
     * @param ownerUsername the username of the owner of the pantry
     */
    public Pantry(String ownerUsername){
        this.ownerUsername = ownerUsername;
        this.fuds = new ArrayList<>();
        this.guestsUsernames = new ArrayList<>();
        this.id = 0;
    }

    /**
     * Constructor
     * @param id the id of the pantry
     * @param ownerUsername the username of the owner of the pantry
     * @param f the list of foods in the pantry
     * @param g the list of guests of the pantry
     */
    public Pantry(int id, String ownerUsername, List<Food> f, List<String> g){
        this.ownerUsername = ownerUsername;
        this.fuds = f;
        this.guestsUsernames = g;
        this.id = id;
    }

    /**
     * Get the id of the pantry
     * @return the id of the pantry
     */
    public int getId(){
        return this.id;
    }

    /**
     * Get the username of the owner of the pantry
     * @return the username of the owner of the pantry
     */
    public String getOwnerUsername(){
        return this.ownerUsername;
    }

    /**
     * Get the list of foods in the pantry
     * @return the list of foods in the pantry
     */
    public List<Food> getFuds(){
        return this.fuds;
    }

    /**
     * Get the list of guests of the pantry
     * @return the list of guests of the pantry
     */
    public List<String> getGuestsUsernames(){
        return this.guestsUsernames;
    }

    /**
     * Set the id of the pantry
     * @param id the id of the pantry
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set the username of the owner of the pantry
     * @param ownerUsername the username of the owner of the pantry
     */
    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    /**
     * Set the list of foods in the pantry
     * @param fuds the list of foods in the pantry
     */
    public void setFuds(List<Food> fuds) {
        this.fuds = fuds;
    }

    /**
     * Set the list of guests of the pantry
     * @param guestsUsernames the list of guests of the pantry
     */
    public void setGuestsUsernames(List<String> guestsUsernames) {
        this.guestsUsernames = guestsUsernames;
    }

    /**
     * Add a food to the pantry
     * @param f the food to add
     */
    public void addFood(Food f){
        this.fuds.add(f);
    }

    /**
     * Remove a food from the pantry
     * @param f the food to remove
     * @return true if the food was removed, false otherwise
     */
    public boolean removeFood(Food f){
        return this.fuds.remove(f);
    }

    /**
     * Add a guest to the pantry
     * @param guestUsername the username of the guest to add
     */
    public void addGuest(String guestUsername){
        this.guestsUsernames.add(guestUsername);
    }

    /**
     * Remove a guest from the pantry
     * @param guestUsername the username of the guest to remove
     * @return true if the guest was removed, false otherwise
     */
    public boolean removeGuest(String guestUsername){
        return this.guestsUsernames.remove(guestUsername);
    }

    /**
     * Returns a string representation of the pantry
     * @return a string representation of the pantry
     */
    public String toString(){
        return "Owner ID: " + this.ownerUsername + " Foods len: " + this.fuds.size()
                + " Guests len: " + this.guestsUsernames.size();
    }

}