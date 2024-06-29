package it.unisannio.ingsw24.gateway;

import it.unisannio.ingsw24.gateway.security.PasswordEncoderBase64;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

public class TestGateway {

    public static void main(String[] args) throws IOException{

        PasswordEncoder pe = new PasswordEncoderBase64();

        String s = pe.encode("big");


        System.out.println("s = " + s);

        boolean match = pe.matches("pipppo", "cGlwcHBv");

        System.out.println("match = " + match);

    }










}

    /*
    public static void main(String[] args) throws IOException {

        String unPackedHost = System.getenv("UNPACKED_HOST");
        String unPackedPort = System.getenv("UNPACKED_PORT");
        if (unPackedHost == null) {
            unPackedHost = "localhost";
        }
        if (unPackedPort == null) {
            unPackedPort = "8082";
        }

        String unPackedAddress = "http://" + unPackedHost + ":" + unPackedPort;

        OkHttpClient client = new OkHttpClient();

        String URLunPacked = String.format(unPackedAddress + "/api/unpacked/olio d'oliva");

        Request request = new Request.Builder()
                .url(URLunPacked)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        System.out.println(response.code());

        ResponseBody bodyResponse = response.body();
        if (bodyResponse == null)
            throw new IOException("Response body is null");

        Gson gson = GsonProvider.createGson();
        String body = response.body().string();
        UnPackedFood carmine = gson.fromJson(body, UnPackedFood.class);

        System.out.println(carmine);

        String jsonC = carmine.toJson();
        System.out.println(jsonC);

        System.out.println("{\"name\": \"mela\",\"id\": 1111,\"isExpired\": false,\"isFridge\": false,\"quantity\": 2,\"category\": \"FRUIT\",\"averageExpirationDays\": \"30\",\"expirationDate\": \"30\"}");

        MediaType mediaType = MediaType.parse("application/json");


        RequestBody bodyRequest = RequestBody.create(mediaType, jsonC);

        String URL = "http://localhost:8084/api/pantry/4/foods/unpacked";
        request = new Request.Builder()
                .url(URL)
                .put(bodyRequest)
                .addHeader("Content-Type", "application/json")
                .build();

        response = client.newCall(request).execute();
        System.out.println(response.code());
        if (response.code() == 400)
            System.out.println("gcc");
        else
            System.out.println("fcc");


    }
}

class GsonProvider {
    public static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new it.unisannio.ingsw24.gateway.logic.LocalDateTypeAdapter())
                .create();
    }
}*/


