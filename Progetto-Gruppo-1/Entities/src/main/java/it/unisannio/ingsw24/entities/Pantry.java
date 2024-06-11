package it.unisannio.ingsw24.entities;

import java.util.ArrayList;
import java.util.List;

public class Pantry {

    private int id;
    private List<Food> fuds;
    private String ownerUsername;
    private List<String> guestsUsernames;

    public Pantry(){
    }

    public Pantry(String ownerUsername){
        this.ownerUsername = ownerUsername;
        this.fuds = new ArrayList<>();
        this.guestsUsernames = new ArrayList<>();
        this.id = 0;
    }

    public Pantry(int id, String ownerUsername, List<Food> f, List<String> g){
        this.ownerUsername = ownerUsername;
        this.fuds = f;
        this.guestsUsernames = g;
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String getOwnerUsername(){
        return this.ownerUsername;
    }

    public List<Food> getFuds(){
        return this.fuds;
    }

    public List<String> getGuestsUsernames(){
        return this.guestsUsernames;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public void setFuds(List<Food> fuds) {
        this.fuds = fuds;
    }

    public void setGuestsUsernames(List<String> guestsUsernames) {
        this.guestsUsernames = guestsUsernames;
    }

    public void addFood(Food f){
        this.fuds.add(f);
    }

    public boolean removeFood(Food f){
        return this.fuds.remove(f);
    }

    public void addGuest(String guestUsername){
        this.guestsUsernames.add(guestUsername);
    }

    public boolean removeGuest(String guestUsername){
        return this.guestsUsernames.remove(guestUsername);
    }

    public String toString(){
        return "Owner ID: " + this.ownerUsername + " Foods len: " + this.fuds.size()
                + " Guests len: " + this.guestsUsernames.size();
    }

}