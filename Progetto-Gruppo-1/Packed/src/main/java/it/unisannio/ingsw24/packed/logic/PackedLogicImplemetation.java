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

public class PackedLogicImplemetation implements PackedLogic{

    private final String openFoodAddress;

    public PackedLogicImplemetation(){
        openFoodAddress = "https://world.openfoodfacts.net/api/v2/product/";
    }

    private OpenFood getOpenFood(String barcode) {
        try{
            // 3017624010701?fields=product_name,nutrition_grades,brands
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
