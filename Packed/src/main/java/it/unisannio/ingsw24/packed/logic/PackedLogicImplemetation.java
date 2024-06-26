package it.unisannio.ingsw24.packed.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.unisannio.ingsw24.entities.OpenFood;
import it.unisannio.ingsw24.entities.PackedFood;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class implements the PackedLogic interface, providing the methods to interact with the packed food database.
 */
public class PackedLogicImplemetation implements PackedLogic{

    private final String openFoodAddress;

    /**
     * This constructor initializes the openFoodAddress field.
     */
    public PackedLogicImplemetation(){
        openFoodAddress = "https://world.openfoodfacts.net/api/v2/product/";
    }

    /**
     * This method returns the OpenFood object with the given barcode.
     * @param barcode the barcode of the packed food
     * @return the OpenFood object with the given barcode
     */
    private OpenFood getOpenFood(String barcode) {
        try{

            String URL = String.format(openFoodAddress + barcode + "?fields=product_name,nutrition_grades,brands");
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            if(response.code() != 200){
                return null; //factory(?)
            }

            Gson gson = new Gson();
            String body = response.body().string();
            OpenFood op = gson.fromJson(body, OpenFood.class);
            return op;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PackedFood getPackedFood(String barcode) {
        OpenFood of = getOpenFood(barcode);

        assert of != null;

        return new PackedFood(of.getProduct().getProductName(), barcode, LocalDate.now(),
                false, false, 1, of.getProduct().getBrands(), of.getProduct().getNutritionGrades());
    }
}



class GsonProvider {
    public static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
    }
}

/**
 * We need to create a custom TypeAdapter for LocalDate because Gson does not support it by default.
 */
class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value.format(formatter));
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString(), formatter);
    }
}
