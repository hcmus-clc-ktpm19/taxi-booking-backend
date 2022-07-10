package com.hcmus.wiberback.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class JwtUtil {

  @Value("${jwt.secret-key}")
  private String secret;

  private static final long THIRTY_SECONDS = 30_000L;
  private static final long EIGHT_HOURS = 28_800_000L;
  private static final long THREE_MONTHS = 7_889_400_000L;

  private Algorithm setAlgorithm() {
    return Algorithm.HMAC256(secret.getBytes());
  }

  public String generateToken(String username, String issuer) {
    // Access token expires in 8 hour
    Date expiredDate = new Date(System.currentTimeMillis() + EIGHT_HOURS);

    return JWT.create()
        .withSubject(username)
        .withExpiresAt(expiredDate)
        .withIssuer(issuer)
        .sign(setAlgorithm());
  }

  public String generateRefreshToken(String username, String issuer) {
    // Refresh token expires in 3 months
    Date refreshTokenExpiredDate = new Date(System.currentTimeMillis() + THREE_MONTHS);

    return JWT.create()
        .withSubject(username)
        .withExpiresAt(refreshTokenExpiredDate)
        .withIssuer(issuer)
        .sign(setAlgorithm());
  }

  public DecodedJWT verifyToken(String token) {
    return JWT.require(setAlgorithm()).build().verify(token);
  }
}
