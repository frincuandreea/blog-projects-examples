package com.example.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTTokenComponent{

  private static final String TOKEN_DATA = "tokenData";
  @Autowired
  private ApplicationProperties applicationProperties;

  private JwtParser jwtParser;

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  private Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getJwtParser().parseClaimsJws(token).getBody();
    return claimsResolver.apply(claims);
  }

  public String generateJwtToken(Object tokenData, int validityInSeconds) {
    Date date = new Date();
    Date expireDate = new Date(date.getTime() + validityInSeconds*1000);
    Map<String, Object> claims =new HashMap<>();
    claims.put(TOKEN_DATA, tokenData);


    return Jwts.builder().setClaims(claims)
        .setIssuedAt(date).setExpiration(expireDate)
        .signWith(getSigningKey()).compact();
  }

  public <T> T decodeJwtToken(String token, Class<T> requiredType) {

    if(isTokenExpired(token)){
      return null;
    }
    Object data = getJwtParser().parseClaimsJws(token).getBody().get(TOKEN_DATA);
    ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(data,requiredType);
  }

  private Key getSigningKey(){
    byte[] secret = Base64.getEncoder().encode(applicationProperties.getJwtSecret().getBytes());
    return new SecretKeySpec(secret, SignatureAlgorithm.HS512.getJcaName());
  }

  private JwtParser getJwtParser(){
    if(jwtParser == null){
      jwtParser = Jwts.parserBuilder().setSigningKey(getSigningKey()).build();
    }
    return jwtParser;
  }
}