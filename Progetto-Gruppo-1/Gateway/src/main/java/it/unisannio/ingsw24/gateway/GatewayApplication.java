package it.unisannio.ingsw24.gateway;

import it.unisannio.ingsw24.gateway.presentation.GatewayService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ApplicationPath("ingsw24")
public class GatewayApplication extends ResourceConfig {

    public GatewayApplication(){
        register(GatewayService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
