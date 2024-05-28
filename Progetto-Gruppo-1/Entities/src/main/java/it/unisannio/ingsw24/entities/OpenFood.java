package it.unisannio.ingsw24.entities;

import java.time.LocalDate;

public class OpenFood extends Food {

    private String brand;
    private String nutritionGrade;

    //private Image image;


    public OpenFood(String name, String id, LocalDate expirationDate, boolean isExpired, boolean isFridge, int quantity, String brand, String nutritionGrade) {
        super(name, id, expirationDate, isExpired, isFridge, quantity);
        this.brand = brand;
        this.nutritionGrade = nutritionGrade;
    }

    @Override
    public void setExpirationDate(String date){
        LocalDate ld = LocalDate.parse(date);
        this.expirationDate = ld;
    }

    public String getBrand(){
        return this.brand;
    }

    public String getNutritionGrade(){
        return this.nutritionGrade;
    }

    public String toString(){
        return super.toString() + " Brand: " + this.brand + " Nutrition Grade: " + this.nutritionGrade;
    }
}
