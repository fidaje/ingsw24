package it.unisannio.ingsw24.gateway.logic;

import com.google.gson.Gson;
import it.unisannio.ingsw24.entities.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GatewayLogicImplementation implements GatewayLogic {

    private final String unPackedAddress;
    private final String packedFoodAddress;
    private final String pantryAddress;
    private final String userAddress;


    public GatewayLogicImplementation() {
        String unPackedHost = System.getenv("UNPACKED_HOST");
        String unPackedPort = System.getenv("UNPACKED_PORT");
        if (unPackedHost == null) {
            unPackedHost = "172.31.6.1";
        }
        if (unPackedPort == null) {
            unPackedPort = "8082";
        }
        unPackedAddress = "http://" + unPackedHost + ":" + unPackedPort;

        String packedHost = System.getenv("PACKED_HOST");
        String packedPort = System.getenv("PACKED_PORT");
        if (packedHost == null) {
            packedHost = "172.31.6.1";
        }
        if (packedPort == null) {
            packedPort = "8085";
        }
        packedFoodAddress = "http://" + packedHost + ":" + packedPort;

        String pantryHost = System.getenv("PANTRY_HOST");
        String pantryPort = System.getenv("PANTRY_PORT");
        if (pantryHost == null) {
            pantryHost = "172.31.6.1";
        }
        if (pantryPort == null) {
            pantryPort = "8084";
        }
        pantryAddress = "http://" + pantryHost + ":" + pantryPort;

        String userHost = System.getenv("USER_HOST");
        String userPort = System.getenv("USER_PORT");
        if (userHost == null) {
            userHost = "172.31.6.1";
        }
        if (userPort == null) {
            userPort = "8089";
        }
        userAddress = "http://" + userHost + ":" + userPort;
    }

    @Override
    public String createUser(MyUser user){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(userAddress + "/rest/users");

            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            RequestBody body = RequestBody.create(mediaType, gson.toJson(user));
            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 201) {
                return null;
            }

            return user.getUsername();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int createPantry(Pantry pantry){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(pantryAddress + "/api/pantry");

            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = GsonProvider.createGson();
            RequestBody body = RequestBody.create(mediaType, gson.toJson(pantry));
            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 201) {
                return 0;
            }
            
            String location = response.header("Location");
            String idString = location.substring(location.lastIndexOf("/")).substring(1);

            return Integer.parseInt(idString);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public MyUser getUser(String username){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(userAddress + "/rest/users/" + username);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            ResponseBody bodyResponse = response.body();
            if (bodyResponse == null)
                throw new IOException("Response body is null");

            Gson gson = new Gson();
            String body = response.body().string();
            MyUser u = gson.fromJson(body, MyUser.class);
            return u;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
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
                foods.add(foodFromJson(fudObject));
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
    public List<Pantry> getPantries(String username){

        try {
            String URL = String.format(pantryAddress + "/api/pantry/pantries/" + username);
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
                pantry.setOwnerUsername(pantryObject.getString("ownerUsername"));

                JSONArray fudsArray = pantryObject.getJSONArray("fuds");
                List<Food> foods = new ArrayList<>();

                for (int j = 0; j < fudsArray.length(); j++){
                    JSONObject fudObject = fudsArray.getJSONObject(j);
                    foods.add(foodFromJson(fudObject));
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

    /**
     * This method creates a Food object from a JSONObject
     * @param foodObject the JSONObject to be converted
     * @return the Food object
     */
    private Food foodFromJson(JSONObject foodObject) {

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

    /**
     * This method creates a List of guests from a JSONObject
     * @param pantryObject the JSONObject of the pantry
     * @return the guests of the pantry
     */
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
                foods.add(foodFromJson(fudObject));
            }

            return foods;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Food getFoodFromPantryByName(int pantryId, String name){
        try {
            OkHttpClient client = new OkHttpClient();
            String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString()).replace("+", "%20");
            String URL = String.format("%s/api/pantry/%s/foods/%s", pantryAddress, pantryId, encodedName);


            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            ResponseBody bodyResponse = response.body();
            if (bodyResponse == null)
                throw new IOException("Response body is null");

            String responseBodyString = bodyResponse.string();

            JSONObject fudObject = new JSONObject(responseBodyString);
            Food f = foodFromJson(fudObject);
            
            return f;

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
                foods.add(foodFromJson(fudObject));
            }

            return foods;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public UnPackedFood getUnPackedFood(String name){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(unPackedAddress + "/api/unpacked/" + name);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            ResponseBody bodyResponse = response.body();
            if (bodyResponse == null)
                throw new IOException("Response body is null");

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
    public PackedFood getPackedFood(String barcode){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(packedFoodAddress + "/api/packed/" + barcode);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            ResponseBody bodyResponse = response.body();
            if (bodyResponse == null)
                throw new IOException("Response body is null");

            Gson gson = GsonProvider.createGson();
            String body = response.body().string();
            PackedFood pf = gson.fromJson(body, PackedFood.class);
            return pf;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePassword(String username, String password){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(userAddress + "/rest/users/" + username);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, password);
            Request request = new Request.Builder()
                    .url(URL)
                    .put(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 400 ){
                return true;
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer updateFoods(int pantryId, Food f, String type){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(pantryAddress + "/api/pantry/" + pantryId + "/foods/" + type);
            MediaType mediaType = MediaType.parse("application/json");
            String jsonFood = f.toJson();
            RequestBody body = RequestBody.create(mediaType, jsonFood);
            Request request = new Request.Builder()
                    .url(URL)
                    .put(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() != 200 ){
                return null;
            }

            //String location = response.header("Location");
            //String idString = location.substring(location.lastIndexOf("/") + 1);
            //return Integer.parseInt(idString);
            return 1;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateGuests(int pantryId, String username){
        try {
            MyUser user = getUser(username);
            if (user != null) {
                OkHttpClient client = new OkHttpClient();
                String URL = String.format(pantryAddress + "/api/pantry/" + pantryId + "/guests");
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, username);
                Request request = new Request.Builder()
                        .url(URL)
                        .put(body)
                        .addHeader("Content-Type", "application/json")
                        .build();

                Response response = client.newCall(request).execute();

                if (response.code() != 200) {
                    return false;
                }
                return true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteFoodByName(int pantryId, String foodName){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(pantryAddress + "/api/pantry/" + pantryId + "/foods/" + foodName);

            Request request = new Request.Builder()
                    .url(URL)
                    .delete()
                    .build();
            Response response = client.newCall(request).execute();

            if (response.code() == 204)
                return true;

        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteGuestByUsername(int pantryId, String username){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(pantryAddress + "/api/pantry/" + pantryId + "/guests/" + username);

            Request request = new Request.Builder()
                    .url(URL)
                    .delete()
                    .build();
            Response response = client.newCall(request).execute();

            if (response.code() == 204)
                return true;

        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePantry(int pantryId){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(pantryAddress + "/api/pantry/" + pantryId);

            Request request = new Request.Builder()
                    .url(URL)
                    .delete()
                    .build();
            Response response = client.newCall(request).execute();

            if (response.code() == 204)
                return true;
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkUsername(int pantryId, String username){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(pantryAddress + "/api/pantry/check/" + pantryId + "/" + username);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            if (response.code() == 200)
                return true;

        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkOwner(int pantryId, String ownerUsername){
        try {
            OkHttpClient client = new OkHttpClient();
            String URL = String.format(pantryAddress + "/api/pantry/check/owner/" + pantryId + "/" + ownerUsername);

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            if (response.code() == 200)
                return true;
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}


