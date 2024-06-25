package it.unisannio.ingsw24.user;

import it.unisannio.ingsw24.user.presentation.UserService;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@ApplicationPath("/rest")
public class UserApplication extends ResourceConfig {

    public UserApplication(){
        register(UserService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
