package com.mullen.nurser.services;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {
    @Value("${jwt.security.key}")
    private String jwtSecretKeyBase64;

    private static final long EXPIRATION_TIME = 3600L;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 604800L;

    public String generateAccessToken(UUID userId, String userEmail) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(jwtSecretKeyBase64);

            JWSSigner signer = new MACSigner(keyBytes);

            Instant now = Instant.now();

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userEmail)
                .claim("user_id", userId.toString())
                .issuer("nurser-api")
                .issueTime(Date.from(now))
                .expirationTime(Date.from(now.plusSeconds(EXPIRATION_TIME)))
                .build();

            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();

            SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            signedJWT.sign(signer);

            return signedJWT.serialize();

        } catch (Exception e) {
            throw new RuntimeException("Falha ao gerar o token JWT", e);
        }
    }


    public String generateRefreshToken(UUID userId) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(jwtSecretKeyBase64);
            JWSSigner signer = new MACSigner(keyBytes);

            Instant now = Instant.now();

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .claim("user_id", userId.toString())
                .issuer("nurser-api")
                .issueTime(Date.from(now))
                .expirationTime(Date.from(now.plusSeconds(REFRESH_TOKEN_EXPIRATION_TIME)))
                .build();

            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();

            SignedJWT signedJWT = new SignedJWT(header, claimsSet);
            signedJWT.sign(signer);

            return signedJWT.serialize();

        } catch (Exception e) {
            throw new RuntimeException("Falha ao gerar o Refresh Token", e);
        }
    }
}

