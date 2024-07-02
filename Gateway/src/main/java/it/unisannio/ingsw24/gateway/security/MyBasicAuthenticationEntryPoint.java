package it.unisannio.ingsw24.gateway.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is used to handle the authentication procedure.
 * It extends the BasicAuthenticationEntryPoint class and overrides the commence method.
 * The commence method is called when the user is not authenticated and the server requires authentication.
 * The method sets the realm name and the status of the response to SC_UNAUTHORIZED.
 * It also writes the message of the exception to the response.
 */
@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    Logger logger = LoggerFactory.getLogger(MyBasicAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
        throws IOException, SecurityException{
        logger.info("Authentication Procedure");
        response.addHeader("WWW-Authenticate", "Basic Realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final PrintWriter writer = response.getWriter();
        writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " - " + authenticationException.getMessage());
    }

    @Override
    public void afterPropertiesSet(){
        setRealmName("NomeApp");
        super.afterPropertiesSet();
    }
}
