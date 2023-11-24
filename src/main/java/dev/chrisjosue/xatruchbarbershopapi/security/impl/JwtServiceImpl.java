package dev.chrisjosue.xatruchbarbershopapi.security.impl;

import dev.chrisjosue.xatruchbarbershopapi.security.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    private static final int MINUTES = 15;

    @Value("${SECRET_KEY}")
    private String secretKey;

    @Override
    public boolean isValidToken(String jwt, UserDetails userDetails) {
        var isExpired = getExpirationDate(jwt).before(new Date());
        var username = getUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isExpired);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new LinkedHashMap<>(), userDetails);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        Date issuedAt = new Date(System.currentTimeMillis());
        return Jwts
                .builder()
                .header()
                .type("JWT")
                .and()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(issuedAt)
                .expiration(new Date(
                        issuedAt.getTime() + (MINUTES * 60 * 1000)
                ))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public String getUsername(String jwt) {
        return getClaims(jwt, Claims::getSubject);
    }

    @Override
    public Date getExpirationDate(String jwt) {
        return getClaims(jwt, Claims::getExpiration);
    }

    /**
     * Extract a specific claim from a token.
     */
    private <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all claims from token
     */
    private Claims getAllClaims(String jwt) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    /**
     * SignIn Key
     */
    public SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
