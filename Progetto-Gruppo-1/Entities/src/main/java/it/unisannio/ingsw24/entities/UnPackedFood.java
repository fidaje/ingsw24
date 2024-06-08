package it.unisannio.ingsw24.entities;

import org.bson.Document;

import java.time.LocalDate;


public class UnPackedFood extends Food {

    private int id;
    private Category type;
    private String averageExpirationDays;

    public UnPackedFood(String name, int id, boolean isExpired, boolean isFridge, int quantity, Category type, String averageExpirationDays) {
        super(name, isExpired, isFridge, quantity);
        this.id = id;
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

    public int getId(){
        return this.id;
    }

    public String getAverageExpirationDays(){
        return this.averageExpirationDays;
    }

    public String toString(){
        return super.toString() + " Average Expire Days:" + this.averageExpirationDays + " Category: " + this.type;
    }

    @Override
    public Document toDocument() {
        return super.toDocument()
                .append("id", id)
                .append("Category", type)
                .append("AverageExpirationDays", averageExpirationDays);
    }
}
