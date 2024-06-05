package it.unisannio.ingsw24.pantry;

import it.unisannio.ingsw24.pantry.presentation.*;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@ApplicationPath("api")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class PantryApplication extends ResourceConfig {

    public PantryApplication(){
        register(PantryService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PantryApplication.class, args);
    }

}
