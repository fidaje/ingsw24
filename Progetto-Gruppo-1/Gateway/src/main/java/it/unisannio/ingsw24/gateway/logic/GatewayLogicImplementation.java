package it.unisannio.ingsw24.gateway.logic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unisannio.ingsw24.entities.OpenFood;
import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.entities.UnPackedMySQL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class GatewayLogicImplementation implements GatewayLogic{

    private final String unPackedAddress;
    //private final String openFoodAddress;


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
    }


    // probabilmente questo metodo deve essere un POST verso l'istanza della MongoPantry dell'utente, il GET va fatto a parte
    @Override
    public UnPackedFood getUnPackedFood(String name, boolean isFridge, int quantity){
        UnPackedMySQL upms = this.getUnPackedFoodMySQL(name);

        return new UnPackedFood(name, upms.getID(), false, isFridge,  quantity, upms.getCategory(), Integer.toString(upms.getAverageExipireDays()));
    }


    private UnPackedMySQL getUnPackedFoodMySQL(String name) {
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

            Gson gson = new Gson();
            String body = response.body().string();
            UnPackedMySQL upms = gson.fromJson(body, UnPackedMySQL.class);
            return upms;

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

    @Override
    public OpenFood getOpenFood() {
        return null;
    }
}
