package com.DSI_V1.dsi.services;

import com.DSI_V1.dsi.properties.JwtProperties;
import com.DSI_V1.dsi.services.auth.admin.AdminUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtTokenServices {

    private String SECRET_KEY = "3331c4eb4bc791c407948a02d6da791ae1bc80a3dc2969e1d9ac5e8d8ad32941";
    private Date CURRENT_TIME = new Date(System.currentTimeMillis());
    private Date REFRESH_EXPIRATION =  new Date(System.currentTimeMillis() + 1000 * 60 * 60 *24 * 14);
    private Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + 1000 * 60 * 10);


    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    // END OF EXTRACT USERNAME FROM TOKEN METHOD.

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    // END OF EXTRACT EXPIRATION DATE FROM TOKEN METHOD.

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    // END OF EXTRACT CLAIMS FROM TOKEN METHOD.

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }


    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();



        return createToken(claims, userDetails.getUsername());
    }


    public String generateToken(AdminUserDetails adminUserDetails){
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, adminUserDetails.getUsername());
    }
    public String generateToken(String Username){
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, Username);
    }
    public String generateRefreshToken(AdminUserDetails adminUserDetails){
        Map<String, Object> claims = new HashMap<>();
        return createRefreshToken(claims, adminUserDetails.getUsername());
    }
    public String generateRefreshToken(UserDetails UserDetails){
        Map<String, Object> claims = new HashMap<>();
        return createRefreshToken(claims, UserDetails.getUsername());
    }


    private String createToken(Map<String, Object> claims, String subject){

        Date now = new Date(System.currentTimeMillis());
        Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 10);
        claims.put("jti", UUID.randomUUID().toString());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String createRefreshToken(Map<String, Object> claims, String subject){
        Date now = new Date(System.currentTimeMillis());
        Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60 *24 * 14);
        claims.put("jti", UUID.randomUUID().toString());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    // END OF VALIDATE TOKEN METHOD.
}
