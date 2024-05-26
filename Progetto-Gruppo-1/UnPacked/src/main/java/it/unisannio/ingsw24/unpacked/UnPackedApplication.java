package it.unisannio.ingsw24.unpacked;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ApplicationPath("unpacked")
public class UnPackedApplication extends ResourceConfig {

    public UnPackedApplication(){
        //register();
    }

    public static void main(String[] args) {
        SpringApplication.run(UnPackedApplication.class, args);
    }

}
