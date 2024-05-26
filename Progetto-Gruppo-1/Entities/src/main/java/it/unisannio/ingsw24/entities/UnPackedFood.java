package it.unisannio.ingsw24.entities;

import java.time.LocalDate;


public class UnPackedFood extends Food {

    Category type;
    int averageExpirationDays;

    public UnPackedFood(String name, String id, LocalDate expirationDate, boolean isExpired, boolean isFridge, int quantity, Category type, int averageExpirationDays) {
        super(name, id, expirationDate, isExpired, isFridge, quantity);
        this.type = type;
        this.averageExpirationDays = averageExpirationDays;
        setExpirationDate();
    }



    @Override
    public void setExpirationDate() {
        LocalDate today = LocalDate.now();
        expirationDate = today.plusDays(averageExpirationDays);
    }

    public String toString(){
        return super.toString() + "Average Ex:" + this.averageExpirationDays;
    }
}
