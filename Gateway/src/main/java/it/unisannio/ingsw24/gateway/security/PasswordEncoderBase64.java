package it.unisannio.ingsw24.gateway.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Base64;

/**
 * This class is used to encode the password of the user.
 * It implements the PasswordEncoder interface and overrides the encode and matches methods.
 * The encode method encodes the password using Base64 encoding.
 * The matches method checks if the encoded password is equal to the given password.
 */
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
