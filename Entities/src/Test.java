import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        Aliment a = new UnPackedAliment("Banana", "123", LocalDate.now(), false, false, 5, Category.FRUIT, 5);
        System.out.println(a);
    }
}
