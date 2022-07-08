package com.hcmus.wiberback.security.filter;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

  Logger logger = LoggerFactory.getLogger(CustomAuthorizationFilter.class);

  @Value("${secret-key}")
//  private String secretKey = "DE2D6323AD62BD9F8A8BF55765DD7";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    if (request.getServletPath().equals("/api/login")) {
      filterChain.doFilter(request, response);
    } else {
      String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

      if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
        try {
          String token = authorizationHeader.substring("Bearer ".length());

          Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
          JWTVerifier verifier = JWT.require(algorithm).build();
          DecodedJWT jwt = verifier.verify(token);

          String username = jwt.getSubject();
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              username, null, null);
          SecurityContextHolder.getContext().setAuthentication(authentication);
          filterChain.doFilter(request, response);
        } catch (Exception exception) {
          logger.error("Error logging in: {}", exception.getMessage());
          response.setHeader("error", exception.getMessage());
          response.setStatus(FORBIDDEN.value());
          //response.sendError(FORBIDDEN.value());
          Map<String, String> error = new HashMap<>();
          error.put("error_message", exception.getMessage());
          response.setContentType(APPLICATION_JSON_VALUE);
          new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
      } else {
        filterChain.doFilter(request, response);
      }
    }
  }
}
