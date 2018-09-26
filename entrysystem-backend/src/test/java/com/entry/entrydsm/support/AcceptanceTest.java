package com.entry.entrydsm.support;

import com.entry.entrydsm.common.response.JwtToken;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.security.jwt.Jwt;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.domain.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AcceptanceTest {

    protected final String DEFAULT_USER_EMAIL = "entrydsm@dsm.hs.kr";
    protected final String DEFAULT_USER_PASSWORD = "password1234";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Jwt jwt;

    @Value("${jwt.prefix}")
    private String jwtHeaderPrefix;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        userRepository.save(User.builder()
                .email(DEFAULT_USER_EMAIL)
                .password(passwordEncoder.encode(DEFAULT_USER_PASSWORD))
                .build());
    }

    private TestRestTemplate template() {
        return template;
    }

    private HttpHeaders defaultUserAuthorizationHeader() throws Exception {
        return prefixedAuthorizationHeader(jwt.createToken(defaultUser()));
    }

    protected HttpHeaders prefixedAuthorizationHeader(JwtToken token) {
        return authorizationHeader(jwtHeaderPrefix + " " + token.getAccessToken());
    }

    protected HttpHeaders authorizationHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        return headers;
    }

    protected User defaultUser() {
        return userRepository.findByEmail(DEFAULT_USER_EMAIL).get();
    }

    protected <T, R> ResponseEntity<RestResponse<R>> postRequest(String url, T dto, ParameterizedTypeReference<RestResponse<R>> typeRef) {
        return template().exchange(url, HttpMethod.POST, new HttpEntity<>(dto), typeRef);
    }

    protected <T, R> ResponseEntity<RestResponse<R>> putRequest(String url, T dto, ParameterizedTypeReference<RestResponse<R>> typeRef) {
        return template().exchange(url, HttpMethod.PUT, new HttpEntity<>(dto), typeRef);
    }

    protected <T> ResponseEntity<RestResponse<T>> getRequest(String url, ParameterizedTypeReference<RestResponse<T>> typeRef) {
        return template().exchange(url, HttpMethod.GET, null, typeRef);
    }

    protected <T, R> ResponseEntity<RestResponse<R>> postRequestWithAuth(String url, T dto, ParameterizedTypeReference<RestResponse<R>> typeRef) throws Exception {
        return template().exchange(url, HttpMethod.POST, new HttpEntity<>(dto, defaultUserAuthorizationHeader()), typeRef);
    }

    protected <T, R> ResponseEntity<RestResponse<R>> getRequestWithAuth(String url, ParameterizedTypeReference<RestResponse<R>> typeRef) throws Exception {
        return template().exchange(url, HttpMethod.GET, new HttpEntity<>(defaultUserAuthorizationHeader()), typeRef);
    }

    protected <T, R> ResponseEntity<RestResponse<R>> putRequestWithAuth(String url, T dto, ParameterizedTypeReference<RestResponse<R>> typeRef) throws Exception {
        return template().exchange(url, HttpMethod.PUT, new HttpEntity<>(dto, defaultUserAuthorizationHeader()), typeRef);
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }
}
