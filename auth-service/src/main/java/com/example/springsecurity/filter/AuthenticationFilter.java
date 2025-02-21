package com.example.springsecurity.filter;

import com.example.springsecurity.consts.ApplicationConstants;
import com.example.springsecurity.login.model.LoginRequestModel;
import com.example.springsecurity.registration.dto.UserDTO;
import com.example.springsecurity.registration.services.UserService;
import com.example.springsecurity.responsemodel.LoginResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    private final Environment environment;

    public AuthenticationFilter(UserService userService,
                                Environment environment,
                                AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.userService = userService;
        this.environment = environment;

        // Set the login endpoint
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {

        try {
            LoginRequestModel credentials = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UsernameNotFoundException e) {
            try {
                sendErrorResponse(res, HttpServletResponse.SC_UNAUTHORIZED, "User Not found with email " + e.getMessage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String userName = ((User) authResult.getPrincipal()).getUsername();
        UserDTO userDetails = userService.getUserDeailsByEmail(userName);
        SecretKey secretKey = Keys.hmacShaKeyFor(ApplicationConstants.JWT_SECRET_DEFAULT_VALUE.getBytes(StandardCharsets.UTF_8));

        Instant now = Instant.now();
        String token = Jwts.builder().issuer("SPRING_SECURITY").subject("JWT_TOKEN" + userDetails.getEmail())
                .issuedAt(Date.from(now))
                .expiration(Date.from((now.plusMillis(Long.parseLong(Objects.requireNonNull(environment.getProperty("token_expiration_time")))))))
                .signWith(secretKey)
                .compact();

        res.addHeader("Authorization", "Bearer " + token);
        res.setStatus(HttpServletResponse.SC_OK);

        LoginResponseModel response = new LoginResponseModel(HttpServletResponse.SC_OK, "Authentication successful");
        sendResponse(res, HttpServletResponse.SC_OK, response);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid email or password");
    }

    private void sendErrorResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        LoginResponseModel errorResponse = new LoginResponseModel(statusCode, message);
        sendResponse(response, statusCode, errorResponse);
    }

    private void sendResponse(HttpServletResponse response, int statusCode, LoginResponseModel responseBody) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        String jsonResponse = new ObjectMapper().writeValueAsString(responseBody);
        response.getWriter().write(jsonResponse);
    }
}

