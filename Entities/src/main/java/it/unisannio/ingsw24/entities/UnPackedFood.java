package it.unisannio.ingsw24.entities;

import org.bson.Document;

import java.time.LocalDate;
import java.util.Objects;


public class UnPackedFood extends Food {

    private int id;
    private Category category;
    private String averageExpirationDays;

    public UnPackedFood(){
        super();
    }

    public UnPackedFood(String name, int id, boolean isExpired, boolean isFridge, int quantity, Category category, String averageExpirationDays) {
        super(name, isExpired, isFridge, quantity);
        this.id = id;
        this.category = category;
        this.averageExpirationDays = averageExpirationDays;
        setExpirationDate(this.averageExpirationDays);
    }

    public UnPackedFood(String name, int id, boolean isExpired, boolean isFridge, int quantity, Category category, String averageExpirationDays, LocalDate expirationDate) {
        super(name, expirationDate, isExpired, isFridge, quantity);
        this.id = id;
        this.category = category;
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

    public Category getCategory() {
        return this.category;
    }

    public String getAverageExpirationDays(){
        return this.averageExpirationDays;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAverageExpirationDays(String averageExpirationDays){
        this.averageExpirationDays = averageExpirationDays;
    }

    public String toString(){
        return super.toString() + " ID: " + this.id + " Average Expire Days:" + this.averageExpirationDays + " Category: " + this.category;
    }

    @Override
    public boolean equals(Object o){
        UnPackedFood upf = (UnPackedFood) o;
        return super.equals(upf) && this.id == upf.getId();
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public Document toDocument() {
        return super.toDocument()
                .append("id", id)
                .append("category", category)
                .append("averageExpirationDays", averageExpirationDays);
    }

    @Override
    public String toJson(){
        return super.toJson() + "\"id\": " + this.id + ",\"category\": \"" + this.category + "\",\"averageExpirationDays\": \"" + this.averageExpirationDays + "\",\"expirationDate\": \"\"}";
    }

}
