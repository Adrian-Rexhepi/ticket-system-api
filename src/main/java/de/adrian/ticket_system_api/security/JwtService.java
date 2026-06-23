package de.adrian.ticket_system_api.security;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import de.adrian.ticket_system_api.entity.User;

public class JwtService {

    private static final String SECRET = System.getenv("JWT_SECRET");
    private static final long EXPIRATION_TIME = 3600000;

    public String generateToken(User user) {
        
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("role", "USER")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));

        return token;
    }

    public String extractUsername(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public boolean isTokenValid(String token, User user) {
        try {
            String username = extractUsername(token);
            return (username.equals(user.getUsername()) && !isTokenExpired(token));
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            Date expiration = JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token)
                    .getExpiresAt();
            return expiration.before(new Date());
        } catch (JWTVerificationException e) {
            return true;
        }
    }
}
