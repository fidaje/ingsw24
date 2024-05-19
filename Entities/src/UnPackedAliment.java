import java.time.LocalDate;
import java.util.Date;

public class UnPackedAliment extends Aliment {

    Category type;
    int averageExpirationDays;

    public UnPackedAliment(String name, String id, LocalDate expirationDate, boolean isExpired, boolean isFridge, int quantity, Category type, int averageExpirationDays) {
        super(name, id, expirationDate, isExpired, isFridge, quantity);
        this.type = type;
        this.averageExpirationDays = averageExpirationDays;
        setExpirationDate();
    }



    @Override
    public void setExpirationDate() {
        LocalDate today = LocalDate.now();
        expirationDate = today.plusDays(averageExpirationDays);
    }
}
