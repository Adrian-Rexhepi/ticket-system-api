package de.adrian.ticket_system_api.security;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.adrian.ticket_system_api.entity.User;

public class JwtService {

    public String generateToken(User user) {
        
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("role", "USER")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                .sign(Algorithm.HMAC256("secret"));

        return token;
    }

    public String extractUsername(String token) {
        return JWT.require(Algorithm.HMAC256("secret"))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean isTokenValid(String token, User user) {
        String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = JWT.require(Algorithm.HMAC256("secret"))
                .build()
                .verify(token)
                .getExpiresAt();
        return expiration.before(new Date());
    }
}
