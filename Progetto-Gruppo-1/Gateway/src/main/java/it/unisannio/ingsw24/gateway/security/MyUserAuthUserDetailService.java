package it.unisannio.ingsw24.gateway.security;

import com.google.gson.Gson;
import it.unisannio.ingsw24.entities.MyUser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserAuthUserDetailService implements UserDetailsService {

    static String userAddress;

    public MyUserAuthUserDetailService(){
        String host = System.getenv("USERS_HOST");
        String port = System.getenv("USERS_PORT");

        if (host == null){
            host = "localhost";
        }

        if (port == null){
            port = "8089";
        }
        userAddress = "http://" + host + ":" + port;
    }

    public MyUser getUser(String username){

        try{
            String URL = String.format(userAddress + "/rest/users/" + username);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            MyUser user = gson.fromJson(response.body().string(), MyUser.class);
            return user;
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("userName = " + username);

        MyUser u = getUser(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(String authority : u.getRoles()){
            System.out.println(authority);
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }

        return new User(u.getUsername(), u.getPassword(), grantedAuthorities);
    }
}
