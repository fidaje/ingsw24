package it.unisannio.ingsw24.packed;

import it.unisannio.ingsw24.packed.presentation.PackedService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ApplicationPath("api")
@SpringBootApplication
public class PackedApplication extends ResourceConfig {

    public PackedApplication(){
        register(PackedService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PackedApplication.class, args);
    }

}
