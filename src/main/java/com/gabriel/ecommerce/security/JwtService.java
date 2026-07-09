package com.gabriel.ecommerce.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gabriel.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration-hours}")
    private Long expirationHours;

    public String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
            .withSubject(user.getEmail())
            .withClaim("userId", user.getId().toString())
            .withExpiresAt(
                Instant.now().plusSeconds(expirationHours * 3600)
            )
            .sign(algorithm);
    }

    public String validateToken(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        } catch (JWTVerificationException e) {

            return null;

        }
    }

}