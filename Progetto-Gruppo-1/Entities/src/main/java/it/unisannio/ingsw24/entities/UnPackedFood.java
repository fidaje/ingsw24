package it.unisannio.ingsw24.entities;

import org.bson.Document;

import java.time.LocalDate;


public class UnPackedFood extends Food {

    private int id;
    private Category type;
    private String averageExpirationDays;

    public UnPackedFood(){
        super();
    }

    public UnPackedFood(String name, int id, boolean isExpired, boolean isFridge, int quantity, Category type, String averageExpirationDays) {
        super(name, isExpired, isFridge, quantity);
        this.id = id;
        this.type = type;
        this.averageExpirationDays = averageExpirationDays;
        setExpirationDate(this.averageExpirationDays);
    }

    public UnPackedFood(String name, int id, boolean isExpired, boolean isFridge, int quantity, Category type, String averageExpirationDays, LocalDate expirationDate) {
        super(name, expirationDate, isExpired, isFridge, quantity);
        this.id = id;
        this.type = type;
        this.averageExpirationDays = averageExpirationDays;
    }

    @Override
    public void setExpirationDate(String days) {
        int expirationDays = Integer.parseInt(this.averageExpirationDays);
        LocalDate today = LocalDate.now();
        expirationDate = today.plusDays(expirationDays);
    }

    public int getId(){
        return this.id;
    }

    public Category getType() {
        return this.type;
    }

    public String getAverageExpirationDays(){
        return this.averageExpirationDays;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(Category type) {
        this.type = type;
    }

    public void setAverageExpirationDays(String averageExpirationDays){
        this.averageExpirationDays = averageExpirationDays;
    }

    public String toString(){
        return super.toString() + " Average Expire Days:" + this.averageExpirationDays + " Category: " + this.type;
    }

    @Override
    public Document toDocument() {
        return super.toDocument()
                .append("id", id)
                .append("category", type)
                .append("averageExpirationDays", averageExpirationDays);
    }

    @Override
    public String toJson(){
        return super.toJson() + "\"id\": " + this.id + ",\"category\": \"" + this.type + "\",\"averageExpirationDays\": \"" + this.averageExpirationDays + "\",\"expirationDate\": \"\"}";
    }

}
