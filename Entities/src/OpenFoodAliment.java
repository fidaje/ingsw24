import java.time.LocalDate;

public class OpenFoodAliment extends Aliment{

    private String brand;
    private String nutritionGrade;

    //private Image image;


    public OpenFoodAliment(String name, String id, LocalDate expirationDate, boolean isExpired, boolean isFridge, int quantity, String brand, String nutritionGrade) {
        super(name, id, expirationDate, isExpired, isFridge, quantity);
        this.brand = brand;
        this.nutritionGrade = nutritionGrade;
    }

    @Override
    public void setExpirationDate() {

    }
}
