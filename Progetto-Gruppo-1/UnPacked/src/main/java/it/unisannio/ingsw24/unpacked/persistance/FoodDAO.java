package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.Category;

public class FoodDAO {

    private String ID, name;
    private int averageExipireDays;
    private Category category;

    public FoodDAO(String ID, String name, int averageExipireDays, Category category){
        this.ID = ID;
        this.name = name;
        this.averageExipireDays = averageExipireDays;
        this.category = category;
    }

    public String getID() {
        return this.ID;
    }

    public String getName(){
        return this.name;
    }

    public int getAverageExipireDays() {
        return this.averageExipireDays;
    }

    public Category getCategory() {
        return this.category;
    }

    public String toString() {
        return "ID: " + this.ID + "Name: " + this.name + "Average Exipire Days: "+ this.averageExipireDays + "Category: " + this.category ;
    }
}
