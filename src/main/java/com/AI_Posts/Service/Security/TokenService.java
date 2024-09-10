package com.AI_Posts.Service.Security;

import com.AI_Posts.Entity.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${secreto}")
    private String secreto;

    public String gerarToken(UserEntity user){
        try{
            Algorithm algoritimo = Algorithm.HMAC256(secreto);

            String token = JWT.create().withIssuer("api-ai-post").withSubject(user.getEmail()).withExpiresAt(this.generateeExpirationDate()).sign(algoritimo);

            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("Error while authenticating");
        }
    }

    private Instant generateeExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3"));
    }

    public String validarToken(String token){
        try{
            Algorithm algoritimo = Algorithm.HMAC256(secreto);

            return JWT.require(algoritimo).withIssuer("api-ai-post").build().verify(token).getSubject();
        } catch (JWTVerificationException VE){
            return null;
        }
    }
}
