package it.unisannio.ingsw24.entities;

import org.bson.Document;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents an unpacked food item.
 */
public class UnPackedFood extends Food {

    private int id;
    private Category category;
    private String averageExpirationDays;

    /**
     * Default constructor
     */
    public UnPackedFood(){
        super();
    }

    /**
     * Constructor
     * @param name the name of the food
     * @param id the id of the food
     * @param isExpired if the food is expired
     * @param isFridge if the food is in the fridge
     * @param quantity the quantity of the food
     * @param category the category of the food
     * @param averageExpirationDays the average expiration days of the food
     */
    public UnPackedFood(String name, int id, boolean isExpired, boolean isFridge, int quantity, Category category, String averageExpirationDays) {
        super(name, isExpired, isFridge, quantity);
        this.id = id;
        this.category = category;
        this.averageExpirationDays = averageExpirationDays;
        setExpirationDate(this.averageExpirationDays);
    }

    /**
     * Constructor
     * @param name the name of the food
     * @param id the id of the food
     * @param isExpired if the food is expired
     * @param isFridge if the food is in the fridge
     * @param quantity the quantity of the food
     * @param category the category of the food
     * @param averageExpirationDays the average expiration days of the food
     * @param expirationDate the expiration date of the food
     */
    public UnPackedFood(String name, int id, boolean isExpired, boolean isFridge, int quantity, Category category, String averageExpirationDays, LocalDate expirationDate) {
        super(name, expirationDate, isExpired, isFridge, quantity);
        this.id = id;
        this.category = category;
        this.averageExpirationDays = averageExpirationDays;
    }

    /**
     * Set the expiration date of the food
     * @param days the days to add to the current date
     */
    @Override
    public void setExpirationDate(String days) {
        int expirationDays = Integer.parseInt(this.averageExpirationDays);
        LocalDate today = LocalDate.now();
        expirationDate = today.plusDays(expirationDays);
    }

    /**
     * Get the id of the food
     * @return the id of the food
     */
    public int getId(){
        return this.id;
    }

    /**
     * Get the category of the food
     * @return the category of the food
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Get the average expiration days of the food
     * @return the average expiration days of the food
     */
    public String getAverageExpirationDays(){
        return this.averageExpirationDays;
    }

    /**
     * Set the id of the food
     * @param id the id of the food
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set the category of the food
     * @param category the category of the food
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Set the average expiration days of the food
     * @param averageExpirationDays the average expiration days of the food
     */
    public void setAverageExpirationDays(String averageExpirationDays){
        this.averageExpirationDays = averageExpirationDays;
    }


    public String toString(){
        return super.toString() + " ID: " + this.id + " Average Expire Days:" + this.averageExpirationDays + " Category: " + this.category;
    }

    /**
     * Check if two UnPackedFood objects are equal
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        UnPackedFood upf = (UnPackedFood) o;
        return super.equals(upf) && this.id == upf.getId();
    }

    /**
     * Get the hash code of the object
     * @return the hash code of the object
     */
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), id);
    }

    /**
     * Convert the object to a Document
     * @return the Document representing the object
     */
    @Override
    public Document toDocument() {
        return super.toDocument()
                .append("id", id)
                .append("category", category)
                .append("averageExpirationDays", averageExpirationDays);
    }

    /**
     * Convert the object to a JSON string
     * @return the JSON string representing the object
     */
    @Override
    public String toJson(){
        return super.toJson() + "\"id\": " + this.id + ",\"category\": \"" + this.category + "\",\"averageExpirationDays\": \"" + this.averageExpirationDays + "\",\"expirationDate\": \"\"}";
    }

}
