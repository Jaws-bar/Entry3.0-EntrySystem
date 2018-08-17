package com.entry.entrydsm.user;

import com.entry.entrydsm.common.security.Crypto;
import com.entry.entrydsm.common.security.jwt.Jwt;
import com.entry.entrydsm.exception.BadRequestException;
import com.entry.entrydsm.tempuser.model.TempUserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/user/guarantee")
public class UserController {

    @Value("${redisTable}")
    private String redisTable;
    private final UserRepository userRepo;
    private final RedisTemplate<String, String> redisTemplate;
    private final Jwt jwt;
    private final Crypto crypto;

    @Autowired
    public UserController(UserRepository userRepo, RedisTemplate<String, String> redisTemplate, Jwt jwt, Crypto crypto) {
        this.userRepo = userRepo;
        this.redisTemplate = redisTemplate;
        this.jwt = jwt;
        this.crypto = crypto;
    }

    @ApiOperation(value = "로그인 API", response = ResponseToken.class)
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<ResponseToken> createToken(@RequestBody TempUserDTO tempUserDTO) throws Exception {
        User user = userRepo.findByEmailAndPassword(tempUserDTO.getEmail(), crypto.encode(tempUserDTO.getPassword()))
                .orElseThrow(() -> new BadRequestException("User Login Failed"));

        String accessToken = jwt.createToken(user.getId());
        return new ResponseEntity<>(new ResponseToken(accessToken, jwt.createRefreshToken(accessToken)), HttpStatus.OK);
    }

    @ApiOperation(value = "AccessToken 재발급", response = ResponseToken.class)
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<ResponseToken> refreshToken(@RequestHeader(value = "Authorization") String refreshToken) throws Exception {
        String token = refreshToken.split(" ")[1];
        if (redisTemplate.opsForSet().isMember(redisTable, token)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Map<String, Object> map = jwt.authToken(token);
        ResponseToken obj = new ResponseToken();
        obj.setAccessToken(jwt.createToken(String.valueOf(map.get("userId"))));
        return new ResponseEntity<>(obj, HttpStatus.OK);

    }

    @ApiOperation(value = "로그아웃")
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public ResponseEntity<Void> logout(@RequestHeader(value = "Authorization") String refreshToken) {
        redisTemplate.opsForSet().add(redisTable, refreshToken.split(" ")[1]);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
