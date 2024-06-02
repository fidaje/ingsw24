package it.unisannio.ingsw24.entities;

public class Product {
    private String brands;
    private String nutrition_grades;
    private String product_name;

    public String getBrands() {
        return brands;
    }
    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getNutritionGrades() {
        return nutrition_grades;
    }
    public void setNutritionGrades(String nutrition_grades) {
        this.nutrition_grades = nutrition_grades;
    }

    public String getProductName() {
        return product_name;
    }
    public void setProductName(String product_name) {
        this.product_name = product_name;
    }
}
