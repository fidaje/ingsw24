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
    public void setExpirationDate() {

    }
}
