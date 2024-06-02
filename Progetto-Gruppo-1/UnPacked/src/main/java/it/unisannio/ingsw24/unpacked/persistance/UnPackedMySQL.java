package it.unisannio.ingsw24.unpacked.persistance;

import it.unisannio.ingsw24.entities.Category;

public class UnPackedMySQL {

    private int id;

    private String name;
    private int averageExipireDays;
    private Category category;

    public UnPackedMySQL(int id, String name, int averageExipireDays, Category category){
        this.id = id;
        this.name = name;
        this.averageExipireDays = averageExipireDays;
        this.category = category;
    }

    public int getID() {
        return this.id;
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
        return "ID: " + this.id + "\nName: " + this.name + "\nAverage Exipire Days: "+ this.averageExipireDays + "\nCategory: " + this.category ;
    }
}
