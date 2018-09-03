package com.entry.entrydsm.support;

import com.entry.entrydsm.common.security.jwt.Jwt;
import com.entry.entrydsm.user.domain.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    private final String DEFAULT_USER_ID = "DEFAULT_USER_ID";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private Jwt jwt;

    @Value("${jwt.prefix}")
    private String jwtHeaderPrefix;

    @Autowired
    private PasswordEncoder passwordEncoder;

    protected TestRestTemplate template() {
        return template;
    }

    protected HttpHeaders prefixedAuthorizationHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtHeaderPrefix + token);
        return headers;
    }

    protected HttpHeaders authorizationHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        return headers;
    }
}
