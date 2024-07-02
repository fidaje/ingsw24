package it.unisannio.ingsw24.gateway.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * This class is used to log the request and response of the server.
 * It implements the Filter interface and overrides the doFilter method.
 * The doFilter method logs the request and response of the server.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyCustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        System.out.println("Logging Request {" + req.getMethod() + "} : {" + req.getRequestURI() + "}");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Logging Response {" + res.getStatus() + "} : {" + res.getContentType() + "}");
    }
}
