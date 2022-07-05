package com.hcmus.customerservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");

    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(phone,
        password);
    return authenticationManager.authenticate(token);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authentication) throws IOException {

    User user = (User) authentication.getPrincipal();

    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

    // Access token expires in 8 hour
//    Date accessTokenExpiredDate = new Date(System.currentTimeMillis() + 28_800_000L); // 8 hours
     /*TODO: this is for testing, remove it later
     Access token expires in 30 seconds*/
    Date accessTokenExpiredDate = new Date(System.currentTimeMillis() + 30_000L); // 30 seconds
    String accessToken = JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(accessTokenExpiredDate)
        .withIssuer(request.getRequestURL().toString())
        .sign(algorithm);

    // Refresh token expires in 3 months
    Date refreshTokenExpiredDate = new Date(System.currentTimeMillis() + 777_7_600_000L); // 3 months
    String refreshToken = JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(refreshTokenExpiredDate)
        .withIssuer(request.getRequestURL().toString())
        .sign(algorithm);

    Map<String, String> tokens = Map.of("Access-Token", accessToken, "Refresh-Token", refreshToken);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException failed) throws IOException {
    response.setStatus(401);
  }
}