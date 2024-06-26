package it.unisannio.ingsw24.entities;

import org.bson.Document;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents a packed food, extends the Food class
 */
public class PackedFood extends Food {

    private String brand;
    private String id;
    private String nutritionGrade;

    /**
     * Default constructor
     */
    public PackedFood(){
        super();
    }

    /**
     * Constructor
     * @param name name of the food
     * @param id id of the food
     * @param expirationDate expiration date of the food
     * @param isExpired boolean value that indicates if the food is expired
     * @param isFridge boolean value that indicates if the food is in the fridge
     * @param quantity quantity of the food
     * @param brand brand of the food
     * @param nutritionGrade nutrition grade of the food
     */
    public PackedFood(String name, String id, LocalDate expirationDate, boolean isExpired, boolean isFridge, int quantity, String brand, String nutritionGrade) {
        super(name, expirationDate, isExpired, isFridge, quantity);
        this.id = id;
        this.brand = brand;
        this.nutritionGrade = nutritionGrade;
    }

    /**
     * Set the expiration date of the food
     * @param date expiration date of the food
     */
    @Override
    public void setExpirationDate(String date){
        LocalDate ld = LocalDate.parse(date);
        this.expirationDate = ld;
    }

    /**
     * Get the brand of the food
     * @return brand of the food
     */
    public String getBrand(){
        return this.brand;
    }

    /**
     * Get the id of the food
     * @return id of the food
     */
    public String getId(){
        return this.id;
    }

    /**
     * Get the nutrition grade of the food
     * @return nutrition grade of the food
     */
    public String getNutritionGrade(){
        return this.nutritionGrade;
    }

    /**
     * Set the brand of the food
     * @param brand brand of the food
     */
    public void setBrand(String brand){
        this.brand = brand;
    }

    /**
     * Set the id of the food
     * @param id id of the food
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the nutrition grade of the food
     * @param nutritionGrade nutrition grade of the food
     */
    public void setNutritionGrade(String nutritionGrade) {
        this.nutritionGrade = nutritionGrade;
    }

    /**
     * Get the string representation of the object
     * @return a string that represents the object
     */
    public String toString(){
        return super.toString() + " ID: " + this.id + " Brand: " + this.brand + " Nutrition Grade: " + this.nutritionGrade;
    }

    /**
     * Check if two objects are equals
     * @param o object to compare
     * @return true if the objects are equals, false otherwise
     */
    @Override
    public boolean equals(Object o){
        PackedFood pf = (PackedFood) o;
        return super.equals(pf) && this.id.equals(pf.getId());
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
     * @return the Document that represents the object
     */
    @Override
    public Document toDocument() {
        return super.toDocument()
                .append("id", id)
                .append("brand", brand)
                .append("nutritionGrade", nutritionGrade);
    }

    /**
     * Convert the object to a JSON string
     * @return the JSON string that represents the object
     */
    @Override
    public String toJson(){
        return super.toJson() + "\"id\": \"" + this.id + "\",\"brand\": \"" + this.brand + "\",\"nutritionGrade\": \"" + this.nutritionGrade + "\",\"expirationDate\": \"" + this.expirationDate.toString() + "\"}";
    }

}
