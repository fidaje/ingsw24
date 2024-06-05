package it.unisannio.ingsw24.entities;

import java.util.ArrayList;
import java.util.List;

public class Pantry {

    private List<Food> fuds;
    private int idOwner;
    private List<Integer> idGuests;

    public Pantry(int idOwner){
        this.idOwner = idOwner;
        this.fuds = new ArrayList<>();
        this.idGuests = new ArrayList<>();
    }

    public Pantry(int idOwner, List<Food> f, List<Integer> g){
        this.idOwner = idOwner;
        this.fuds = f;
        this.idGuests = g;
    }

    public int getIdOwner(){
        return this.idOwner;
    }

    public List<Food> getFuds(){
        return this.fuds;
    }

    public List<Integer> getIdGuests(){
        return this.idGuests;
    }

    public void addFood(Food f){
        this.fuds.add(f);
    }

    public void addGuest(int idGuest){
        this.idGuests.add(idGuest);
    }

    public int removeGuest(int idGuest){
        return this.idGuests.remove(idGuest);
    }

    public String toString(){
        return "Owner ID: " + this.idOwner + " Foods len: " + this.fuds.size()
                + " Guests len: " + this.idGuests.size();
    }

}