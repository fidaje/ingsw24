package it.unisannio.ingsw24.entities;

/**
 * This class represents an OpenFood object
 */
public class OpenFood {

    private String code;
    private Product product;
    private int status;
    private String status_verbose;

    /**
     * Get the code of the OpenFood object
     * @return code of the OpenFood object
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code of the OpenFood object
     * @param code code of the OpenFood object
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the product of the OpenFood object
     * @return product of the OpenFood object
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set the product of the OpenFood object
     * @param product product of the OpenFood object
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Get the status of the OpenFood object
     * @return status of the OpenFood object
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set the status of the OpenFood object
     * @param status status of the OpenFood object
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Get the status verbose of the OpenFood object
     * @return status verbose of the OpenFood object
     */
    public String getStatusVerbose() {
        return status_verbose;
    }

    /**
     * Set the status verbose of the OpenFood object
     * @param status_verbose status verbose of the OpenFood object
     */
    public void setStatusVerbose(String status_verbose) {
        this.status_verbose = status_verbose;
    }

    /**
     * Get the string representation of the object
     * @return string representation of the object
     */
    public String toString(){
        return "Code: " + code + "\nProduct name: " + product.getProductName()  + "\nProduct brand: " + product.getBrands() + "\nProduct nutrition grades: " + product.getNutritionGrades() + "\nStatus: " + status + "\nStatus verbose: " + status_verbose + "\n";
    }

}


