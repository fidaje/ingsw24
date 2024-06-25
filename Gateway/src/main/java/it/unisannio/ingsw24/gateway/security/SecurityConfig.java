package it.unisannio.ingsw24.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
        http.csrf(csrf -> csrf.disable());
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

        //http.authorizeHttpRequests().requestMatchers("/ingsw24/gateway/**").authenticated().and().httpBasic();
//        http.authorizeHttpRequests().requestMatchers("/hello").permitAll();
        http.authorizeHttpRequests()
                .requestMatchers("/ingsw24/gateway/user/**").permitAll()
                .anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);

      //  http.authorizeHttpRequests().anyRequest().permitAll();


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
        return new PasswordEncoderPlain();
    }
}
