package it.unisannio.ingsw24.entities;

import java.time.LocalDate;
import org.bson.Document;

/**
 * This class represents a generic food.
 * It contains the name, the expiration date, the quantity, the boolean isExpired and isFridge.
 * The class is abstract because it is a generic class and it is not possible to create an instance of it.
 */
public abstract class Food {

    private String name;
    protected LocalDate expirationDate;
    private boolean isExpired;
    private boolean isFridge;
    private int quantity;

    /**
     * Default constructor
     */
    public Food(){}

    /**
     * Constructor
     * @param name the name of the food
     * @param expirationDate the expiration date of the food
     * @param isExpired a boolean that indicates if the food is expired
     * @param isFridge a boolean that indicates if the food is in the fridge
     * @param quantity the quantity of the food
     */
    public Food(String name, LocalDate expirationDate, boolean isExpired, boolean isFridge, int quantity) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.isExpired = isExpired;
        this.isFridge = isFridge;
        this.quantity = quantity;
    }

    /**
     * Constructor
     * @param name the name of the food
     * @param isExpired a boolean that indicates if the food is expired
     * @param isFridge a boolean that indicates if the food is in the fridge
     * @param quantity the quantity of the food
     */
    protected Food(String name, boolean isExpired, boolean isFridge, int quantity) {
        this.name = name;
        this.isExpired = isExpired;
        this.isFridge = isFridge;
        this.quantity = quantity;
    }

    /**
     * Get the name of the food
     * @return the name of the food
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the expiration date of the food
     * @return the expiration date of the food
     */
    public LocalDate getExpirationDate(){
        return this.expirationDate;
    }

    /**
     * Get the boolean isExpired
     * @return the boolean isExpired
     */
    public boolean getIsExpired(){
        return this.isExpired;
    }

    /**
     * Get the boolean isFridge
     * @return the boolean isFridge
     */
    public boolean getIsFridge(){
        return this.isFridge;
    }

    /**
     * Get the quantity of the food
     * @return the quantity of the food
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Set the name of the food
     * @param name the name of the food
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Set the expiration date of the food, is abstract because the expiration date is different for each food
     * @param date the expiration date of the food
     */
    abstract public void setExpirationDate(String date);

    /**
     * Set the boolean isExpired
     */
    public void setIsExpired(){
        LocalDate today = LocalDate.now();
        this.isExpired = today.isAfter(this.expirationDate);
    }

    /**
     * Set the boolean isFridge
     * @param isFridge a boolean that indicates if the food is in the fridge
     */
    public void setIsFridge(boolean isFridge){
        this.isFridge = isFridge;
    }

    /**
     * Set the quantity of the food
     * @param quantity the quantity of the food
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Check if the food is expired
     * @return a boolean that indicates if the food is expired
     */
    @Override
    public boolean equals(Object o){
        Food food = (Food) o;
        return this.name.equals(food.getName());
    }

    /**
     * Override the hashCode method
     */
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }

    /**
     * toString method that returns the name, the expiration date, the boolean isExpired, the boolean isFridge and the quantity of the food
     * @return a string that contains the name, the expiration date, the boolean isExpired, the boolean isFridge and the quantity of the food
     */
    public String toString(){
        return "Name: " + name + " Expiration Date: " + expirationDate + " Is Expired: " + isExpired + " Is Fridge: " + isFridge + " Quantity: " + quantity;
    }

    /**
     * Convert the food to a Document
     * @return a Document that contains the name, the expiration date, the boolean isExpired, the boolean isFridge and the quantity of the food
     */
    public Document toDocument(){
        return new Document("name", name)
                .append("expirationDate", expirationDate.toString())
                .append("isExpired", isExpired)
                .append("isFridge", isFridge)
                .append("quantity", quantity);
    }

    /**
     * Convert the food to a JSON
     * @return a JSON that contains the name, the expiration date, the boolean isExpired, the boolean isFridge and the quantity of the food
     */
    public String toJson(){
        return "{\"name\": \"" + this.name + "\",\"isExpired\": " + this.isExpired + ",\"isFridge\": " + this.isFridge + ",\"quantity\": " + this.quantity + ",";
    }
}
