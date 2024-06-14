package it.unisannio.ingsw24.gateway.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.unisannio.ingsw24.entities.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class GatewayLogicImplementation implements GatewayLogic {

    private final String unPackedAddress;
    private final String packedFoodAddress;
    private final String pantryAddress;


    public GatewayLogicImplementation() {
        String unPackedHost = System.getenv("UNPACKED_HOST");
        String unPackedPort = System.getenv("UNPACKED_PORT");
        if (unPackedHost == null) {
            unPackedHost = "localhost";
        }
        if (unPackedPort == null) {
            unPackedPort = "8082";
        }
        unPackedAddress = "http://" + unPackedHost + ":" + unPackedPort;

        String packedHost = System.getenv("PACKED_HOST");
        String packedPort = System.getenv("PACKED_PORT");
        if (packedHost == null) {
            packedHost = "127.0.0.1";
        }
        if (packedPort == null) {
            packedPort = "8085";
        }
        packedFoodAddress = "http://" + packedHost + ":" + packedPort;

        String pantryHost = System.getenv("PANTRY_HOST");
        String pantryPort = System.getenv("PANTRY_PORT");
        if (pantryHost == null) {
            pantryHost = "127.0.0.1";
        }
        if (pantryPort == null) {
            pantryPort = "8084";
        }

        pantryAddress = "http://" + pantryHost + ":" + pantryPort;
    }

    @Override
    public Pantry getPantry(int pantryId) {

        try {
            String URL = String.format(pantryAddress + "/api/pantry/" + pantryId);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                return null;
            }

            String body = response.body().string();
            JSONObject jsonObject = new JSONObject(body);

            Pantry pantry = new Pantry();
            List<Food> foods = new ArrayList<>();
            pantry.setId(pantryId);

            String ownerUsername = jsonObject.getString("ownerUsername");
            pantry.setOwnerUsername(ownerUsername);

            JSONArray fudsArray = jsonObject.getJSONArray("fuds");
            for (int i = 0; i < fudsArray.length(); i++) {
                JSONObject fudObject = fudsArray.getJSONObject(i);
                foods.add(addFood(fudObject));
            }

            pantry.setFuds(foods);

            //List<String> guests = new ArrayList<>();

            pantry.setGuestsUsernames(getGuests(jsonObject));

            return pantry;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Pantry> getPantries(String ownerUsername){

        try {
            String URL = String.format(pantryAddress + "/api/pantry/pantries/" + ownerUsername);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                return null;
            }

            String body = response.body().string();

            List<Pantry> pantries = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(body);

            for (int i=0; i < jsonArray.length(); i++){
                JSONObject pantryObject = jsonArray.getJSONObject(i);
                Pantry pantry = new Pantry();

                pantry.setId(pantryObject.getInt("id"));
                pantry.setOwnerUsername(ownerUsername);

                JSONArray fudsArray = pantryObject.getJSONArray("fuds");
                List<Food> foods = new ArrayList<>();

                for (int j = 0; j < fudsArray.length(); j++){
                    JSONObject fudObject = fudsArray.getJSONObject(j);
                    foods.add(addFood(fudObject));
                }
                pantry.setFuds(foods);

                pantry.setGuestsUsernames(getGuests(pantryObject));

                pantries.add(pantry);
            }

            return pantries;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Food addFood(JSONObject foodObject) {

        String name = foodObject.getString("name");
        String expirationDate = foodObject.getString("expirationDate");
        boolean isExpired = foodObject.getBoolean("isExpired");
        boolean isFridge = foodObject.getBoolean("isFridge");
        int quantity = foodObject.getInt("quantity");
        if (foodObject.has("brand")) {
            String id = foodObject.getString("id");
            String brand = foodObject.getString("brand");
            String nutritionGrade = foodObject.getString("nutritionGrade");
            PackedFood pf = new PackedFood(name, id, LocalDate.parse(expirationDate), isExpired, isFridge, quantity, brand, nutritionGrade);
            return pf;
        }

        if (foodObject.has("category")) {
            int id = foodObject.getInt("id");
            String category = foodObject.getString("category");
            String averageExpirationDays = foodObject.getString("averageExpirationDays");
            UnPackedFood upf = new UnPackedFood(name, id, isExpired, isFridge, quantity, Category.valueOf(category), averageExpirationDays);
            return upf;
        }

        return null;
    }

    private List<String> getGuests(JSONObject pantryObject){
        JSONArray guestsArray = pantryObject.getJSONArray("guestsUsernames");
        List<String> guestsUsernames = new ArrayList<>();
        for (int k = 0; k < guestsArray.length(); k++) {
            guestsUsernames.add(guestsArray.getString(k));
        }
        return guestsUsernames;
    }

    @Override
    public List<Food> getFoods(int pantryId){
        try {
            String URL = String.format(pantryAddress + "/api/pantry/" + pantryId + "/foods");
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            if(response.code() != 200){
                return null; //factory(?)
            }

            String body = response.body().string();
            JSONArray jsonArray = new JSONArray(body);
            List<Food> foods = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject fudObject = jsonArray.getJSONObject(i);
                foods.add(addFood(fudObject));
            }

            return foods;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Food> getExpiredFoods(int pantryId){

        try {
            String URL = String.format(pantryAddress + "/api/pantry/" + pantryId + "/expiredFoods");
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            if(response.code() != 200){
                return null; //factory(?)
            }

            String body = response.body().string();
            JSONArray jsonArray = new JSONArray(body);
            List<Food> foods = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject fudObject = jsonArray.getJSONObject(i);
                foods.add(addFood(fudObject));
            }

            return foods;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Integer updateFoods(int pantryId, Food f){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(pantryAddress + "/api/pantry/" + pantryId + "/foods/unpacked");
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = GsonProvider.createGson();
            String jsonF = gson.toJson(f);
            RequestBody body = RequestBody.create(mediaType, jsonF);
            Request request = new Request.Builder()
                    .url(URL)
                    .put(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 200 ){
                return null;
            }

            String location = response.header("Location");
            String idString = location.substring(location.lastIndexOf("/") + 1);
            return Integer.parseInt(idString);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateGuests(int pantryId, String username){
        return false;
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
