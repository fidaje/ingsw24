package it.unisannio.ingsw24.entities;

/**
 * This class represents a product.
 */
public class Product {
    private String brands;
    private String nutrition_grades;
    private String product_name;

    /**
     * Returns the brands of the product.
     * @return the brands of the product.
     */
    public String getBrands() {
        return brands;
    }

    /**
     * Sets the brands of the product.
     * @param brands the brands of the product.
     */
    public void setBrands(String brands) {
        this.brands = brands;
    }

    /**
     * Returns the nutrition grades of the product.
     * @return the nutrition grades of the product.
     */
    public String getNutritionGrades() {
        return nutrition_grades;
    }

    /**
     * Sets the nutrition grades of the product.
     * @param nutrition_grades the nutrition grades of the product.
     */
    public void setNutritionGrades(String nutrition_grades) {
        this.nutrition_grades = nutrition_grades;
    }

    /**
     * Returns the name of the product.
     * @return the name of the product.
     */
    public String getProductName() {
        return product_name;
    }

    /**
     * Sets the name of the product.
     * @param product_name the name of the product.
     */
    public void setProductName(String product_name) {
        this.product_name = product_name;
    }
}
