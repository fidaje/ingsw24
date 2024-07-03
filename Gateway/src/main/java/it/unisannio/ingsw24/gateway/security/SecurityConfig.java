package it.unisannio.ingsw24.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class is used to configure the security of the server.
 * It is annotated with @Configuration, @EnableWebSecurity and @EnableMethodSecurity.
 * The @Configuration annotation indicates that the class is a configuration class.
 * The @EnableWebSecurity annotation enables the Spring Security web security support.
 * The @EnableMethodSecurity annotation enables the Spring Security method security support.
 * The class contains a constructor that initializes the userDetailService attribute.
 * The class contains a method that returns the security filter chain.
 * The method disables the CSRF protection and configures the security of the server.
 * The method configures the security of the server based on the path of the request.
 * The method returns the security filter chain.
 * The class contains a method that returns the custom authentication manager.
 * The method initializes the authentication manager builder and sets the user detail service and the password encoder.
 * The method returns the authentication manager.
 * The class contains a method that returns the password encoder.
 * The method returns a new instance of the PasswordEncoderBase64 class.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true, proxyTargetClass = true)
public class SecurityConfig {

    private final MyUserAuthUserDetailService userDetailService;

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //http.httpBasic(Customizer.withDefaults());
        //http.csrf(csrf -> csrf.disable());
        /*
         * No authentication needed
         * */
//        http.authorizeHttpRequests().anyRequest().permitAll();

        /*
         * All request must be authenticated
         * */
//        http.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//        http.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);


        /*
         * Path matching based security
         * */
//        http.authorizeHttpRequests().requestMatchers("/hello.html").permitAll();

        http.authorizeHttpRequests(request -> request.requestMatchers("/ingsw24/gateway/**")
                .authenticated()).httpBasic(Customizer.withDefaults());
//        http.authorizeHttpRequests().requestMatchers("/hello").permitAll();
        //http.authorizeHttpRequests().requestMatchers("/ingsw24/gateway/user/**").authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);

       // http.authorizeHttpRequests().requestMatchers("*.html", "*.css", "*.js").permitAll();
      //  http.authorizeHttpRequests().anyRequest().permitAll();

        http.authorizeHttpRequests(request -> request.requestMatchers("/html/**.html", "/javascript/**.js", "styles.css").permitAll());
        return http.build();
    }

    public SecurityConfig(MyUserAuthUserDetailService myUserAuthUserDetailService){
        this.userDetailService = myUserAuthUserDetailService;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoderBase64();
    }
}
