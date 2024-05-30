package it.unisannio.ingsw24.unpacked;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.unisannio.ingsw24.unpacked.presentation.UnPackedService;

@SpringBootApplication
@ApplicationPath("api")
public class UnPackedApplication extends ResourceConfig {

    public UnPackedApplication(){
        register(UnPackedService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UnPackedApplication.class, args);
    }

}
