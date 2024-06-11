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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
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
                String name = fudObject.getString("name");
                String expirationDate = fudObject.getString("expirationDate");
                boolean isExpired = fudObject.getBoolean("isExpired");
                boolean isFridge = fudObject.getBoolean("isFridge");
                int quantity = fudObject.getInt("quantity");
                if (fudObject.has("brand")) {
                    String id = fudObject.getString("id");
                    String brand = fudObject.getString("brand");  // 'optString' è usato per valori opzionali
                    String nutritionGrade = fudObject.getString("nutritionGrade");

                    PackedFood pf = new PackedFood(name, id, LocalDate.parse(expirationDate), isExpired, isFridge, quantity, brand, nutritionGrade);
                    foods.add(pf);
                }

                if (fudObject.has("category")) {
                    int id = fudObject.getInt("id");
                    String category = fudObject.getString("category");
                    String averageExpirationDays = fudObject.getString("averageExpirationDays");
                    UnPackedFood upf = new UnPackedFood(name, id, isExpired, isFridge, quantity, Category.valueOf(category), averageExpirationDays);
                    foods.add(upf);
                }
            }

            pantry.setFuds(foods);

            List<String> guests = new ArrayList<>();

            JSONArray guestsArray = jsonObject.getJSONArray("guestsUsernames");
            for (int i = 0; i < guestsArray.length(); i++) {
                String guest = guestsArray.getString(i);
                guests.add(guest);
            }

            pantry.setGuestsUsernames(guests);

            return pantry;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}