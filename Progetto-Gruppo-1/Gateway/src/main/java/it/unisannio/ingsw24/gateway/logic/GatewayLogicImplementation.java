package it.unisannio.ingsw24.gateway.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.unisannio.ingsw24.entities.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;



public class GatewayLogicImplementation implements GatewayLogic{

    private final String unPackedAddress;
    private final String openFoodAddress;


    public GatewayLogicImplementation(){
        String unPackedHost = System.getenv("WORKSHOP_HOST");
        String unPackedPort = System.getenv("WORKSHOP_PORT");
        if (unPackedHost == null) {
            unPackedHost = "localhost";
        }
        if (unPackedPort == null) {
            unPackedPort = "8082";
        }
        unPackedAddress = "http://" + unPackedHost + ":" + unPackedPort;

        openFoodAddress = "https://world.openfoodfacts.net/api/v2/product/";
    }


    // probabilmente questo metodo deve essere un POST verso l'istanza della MongoPantry dell'utente, il GET va fatto a parte
    @Override
    public UnPackedFood getUnPackedFood(String name, boolean isFridge, int quantity){
        UnPackedFood upf = this.getUnPackedFood(name);
        upf.setQuantity(quantity);
        upf.setIsFridge(isFridge);

        return upf;
    }


    private UnPackedFood getUnPackedFood(String name) {
        try{
            String URL = String.format(unPackedAddress + "/api/unpacked/" + name);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            if(response.code() != 200){
                return null; //factory(?)
            }

            Gson gson = GsonProvider.createGson();
            String body = response.body().string();
            UnPackedFood upf = gson.fromJson(body, UnPackedFood.class);
            return upf;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Map<String, UnPackedMySQL> getAllUnPackedFood(){
        try {
            String URL = String.format(unPackedAddress + "/api/unpacked");
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
            Map<String, UnPackedMySQL> f = gson.fromJson(body, new TypeToken<Map<String, UnPackedMySQL>>() {}.getType());
            return new HashMap<>(f);

        } catch (IOException e){
        e.printStackTrace();
    }
        return null;
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


    //probabilmente un POST
    @Override
    public PackedFood getOpenFoodPantry(String barcode, String date, boolean isFridge, int quantity){
        OpenFood of = this.getOpenFood(barcode);

        return new PackedFood(of.getProduct().getProductName(), barcode, LocalDate.parse(date),
                false, isFridge, quantity, of.getProduct().getBrands(), of.getProduct().getNutritionGrades());
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
