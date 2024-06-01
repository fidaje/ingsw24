package it.unisannio.ingsw24.gateway.logic;

import com.google.gson.Gson;
import it.unisannio.ingsw24.entities.OpenFood;
import it.unisannio.ingsw24.entities.UnPackedFood;
import it.unisannio.ingsw24.unpacked.persistance.UnPackedMySQL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


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

    @Override
    public UnPackedFood getUnPackedFood(String name) {
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


            int avgExpDays = upms.getAverageExipireDays();
            String avgString = Integer.toString(avgExpDays);

            return new UnPackedFood(upms.getName(), upms.getID(), false, false, 1, upms.getCategory(), avgString);

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
