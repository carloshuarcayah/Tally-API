package pe.com.carlosh.tallyapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService{
    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigninKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails user){
        return Jwts.builder().subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(getSigninKey())
                .compact();
    }

    public String extractUserName(String token){
        return getAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails user){
        String username = getAllClaims(token).getSubject();
        return user.getUsername().equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return  getAllClaims(token).getExpiration().before(new Date());
    }

    private Claims getAllClaims(String token){
        return Jwts.parser().verifyWith(getSigninKey()).build()
                .parseSignedClaims(token).getPayload();
    }
}
