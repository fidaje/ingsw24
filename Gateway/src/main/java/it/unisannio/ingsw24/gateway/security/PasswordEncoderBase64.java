package it.unisannio.ingsw24.gateway.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

public class PasswordEncoderBase64 implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence){
        return Base64.getEncoder().encodeToString(charSequence.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s){
        return encode(charSequence).equals(s);
    }
}
