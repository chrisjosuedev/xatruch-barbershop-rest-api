package dev.chrisjosue.xatruchbarbershopapi.security;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface JwtService {
    Claims verifyJws(String jwt);
    boolean isValidToken (String jwt, UserDetails userDetails);
    String generateToken (UserDetails userDetails);
    String generateToken (Map<String, Object> extraClaims, UserDetails userDetails);
    String getUsername (String jwt);
    Date getExpirationDate (String jwt);
}
