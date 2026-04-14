package pe.com.carlosh.tallyapi.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails user;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "jwtSecretKey",
                "ClavedepruebaparalaspruebasUnitariasdeJwtService123456789");
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 3600000L); // 1 hour

        user = new User("test@mail.com", "pass", Collections.emptyList());
    }

    @Test
    @DisplayName("GenerateToken - Ok: produces non-empty token")
    void generateToken_Success() {
        String token = jwtService.generateToken(user);

        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    @DisplayName("ExtractUserName - Ok: returns subject from token")
    void extractUserName_Success() {
        String token = jwtService.generateToken(user);

        assertEquals("test@mail.com", jwtService.extractUserName(token));
    }

    @Test
    @DisplayName("IsTokenValid - Ok: returns true for valid token and matching user")
    void isTokenValid_Success() {
        String token = jwtService.generateToken(user);

        assertTrue(jwtService.isTokenValid(token, user));
    }

    @Test
    @DisplayName("IsTokenValid - Error: returns false when username does not match")
    void isTokenValid_WrongUser() {
        String token = jwtService.generateToken(user);
        UserDetails otherUser = new User("other@mail.com", "pass", Collections.emptyList());

        assertFalse(jwtService.isTokenValid(token, otherUser));
    }

    @Test
    @DisplayName("IsTokenValid - Error: throws ExpiredJwtException when token is expired")
    void isTokenValid_WhenExpired() {
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", -1000L);
        String expiredToken = jwtService.generateToken(user);

        assertThrows(ExpiredJwtException.class, () -> jwtService.isTokenValid(expiredToken, user));
    }

    @Test
    @DisplayName("IsTokenExpired - Ok: returns false when token is fresh")
    void isTokenExpired_WhenFresh() {
        String token = jwtService.generateToken(user);

        assertFalse(jwtService.isTokenExpired(token));
    }
}