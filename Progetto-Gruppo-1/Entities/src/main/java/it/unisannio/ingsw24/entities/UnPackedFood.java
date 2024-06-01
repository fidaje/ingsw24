package it.unisannio.ingsw24.entities;

import java.time.LocalDate;


public class UnPackedFood extends Food {

    Category type;
    String averageExpirationDays;

    public UnPackedFood(String name, String id, boolean isExpired, boolean isFridge, int quantity, Category type, String averageExpirationDays) {
        super(name, id, isExpired, isFridge, quantity);
        this.type = type;
        this.averageExpirationDays = averageExpirationDays;
        setExpirationDate(this.averageExpirationDays);
    }

    @Override
    public void setExpirationDate(String days) {
        int expirationDays = Integer.parseInt(days);
        LocalDate today = LocalDate.now();
        expirationDate = today.plusDays(expirationDays);
    }

    public String getAverageExpirationDays(){
        return this.averageExpirationDays;
    }

    public String toString(){
        return super.toString() + " Average Expire Days:" + this.averageExpirationDays + " Category: " + this.type;
    }
}
