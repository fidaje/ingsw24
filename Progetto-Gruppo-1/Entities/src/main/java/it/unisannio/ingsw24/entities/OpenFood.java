package it.unisannio.ingsw24.entities;

public class OpenFood {

    private String code;
    private Product product;
    private int status;
    private String status_verbose;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusVerbose() {
        return status_verbose;
    }
    public void setStatusVerbose(String status_verbose) {
        this.status_verbose = status_verbose;
    }

    public String toString(){
        return "Code: " + code + "\nProduct name: " + product.getProductName()  + "\nProduct brand: " + product.getBrands() + "\nProduct nutrition grades: " + product.getNutritionGrades() + "\nStatus: " + status + "\nStatus verbose: " + status_verbose + "\n";
    }

}


