package it.unisannio.ingsw24.gateway.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;

/**
 * This class provides a Gson instance with custom type adapters.
 */
public class GsonProvider {

    /**
     * Creates a Gson instance with custom type adapters.
     * @return a Gson instance with custom type adapters
     */
    public static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
    }
}
