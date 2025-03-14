package com.example.springsecurity.filter;

import com.example.springsecurity.consts.ApplicationConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Environment environment = getEnvironment();
                if (environment != null) {
                    String secret = environment.getProperty(ApplicationConstants.JWT_SECRET, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                    String jwt = Jwts.builder().issuer("SPRING_SECURITY").subject("JWT_TOKEN")
                            .claim("username", authentication.getName())
                            .claim("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                            .issuedAt(new Date())
                            .expiration(new Date((new Date()).getTime() + 300000000))
                            .signWith(secretKey).compact();
                    response.setHeader(ApplicationConstants.JWT_HEADER, jwt);
                }
            }
            response.setStatus(HttpServletResponse.SC_CREATED);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set the response status to 500 Internal Server Error
            response.getWriter().write("Error generating JWT token: " + e.getMessage());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/auth/login");
    }
}
