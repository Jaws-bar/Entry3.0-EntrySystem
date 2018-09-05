package com.entry.entrydsm.common.security.jwt;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.entry.entrydsm.user.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class Jwt {

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.clientId}")
    private String clientId;

    @Value("${jwt.exp}")
    private int exp;

    @Value("${jwt.refreshExp}")
    private int refreshExp;

    @Value("{jwt.secret}")
    private String secret;

    private JWTSigner signer;
    private JWTVerifier jwtVerifier;

    @PostConstruct
    public void initPropertiesSetting() {
        signer = new JWTSigner(Base64.decodeBase64(secret));
        jwtVerifier = new JWTVerifier(Base64.decodeBase64(secret), clientId, issuer);
    }

    private JWTSigner.Options initSetting(Integer exp) {
        JWTSigner.Options options = new JWTSigner.Options();
        options.setAlgorithm(Algorithm.HS512);
        options.setJwtId(true);
        options.setIssuedAt(true);
        options.setExpirySeconds(exp);
        return options;
    }

    public String createToken(User user) {
        return createToken(user.getId());
    }

    public String createToken(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("iss", issuer);
        map.put("userId", userId);
        return signer.sign(map, initSetting(exp));
    }

    public String createRefreshToken(String token) throws Exception {
        Map<String, Object> claims = authToken(token);
        claims.remove("exp");
        claims.remove("iat");
        claims.remove("jti");
        return signer.sign(claims, initSetting(refreshExp));
    }

    public Map<String, Object> authToken(String token) throws Exception {
        return jwtVerifier.verify(token);
    }

    public boolean validation(String token) {
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserId(String token) throws Exception {
        return (String) jwtVerifier.verify(token).get("userId");
    }
}
