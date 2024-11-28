package nefu.laboratory.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;

import nefu.laboratory.dto.Code;
import nefu.laboratory.exception.XException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
@Component
public class JWTComponent {
    private final LocalDateTime time=LocalDateTime.now().plusDays(30);
    @Value("${my.key}")
    private String key;
    private Algorithm algorithm;

    @PostConstruct
    private void init()
    {
        algorithm=Algorithm.HMAC256(key);
    }
    public String encode(Map<String,Object> map){
        return JWT.create()
                .withPayload(map)
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(algorithm);
    }
    public DecodedJWT decode(String token){
        try{
           return JWT.require(algorithm).build().verify(token);
        }catch (TokenExpiredException | SignatureVerificationException e){
            if(e instanceof SignatureVerificationException){
                throw XException.builder().code(Code.FORBIDDEN).build();
            }
            throw XException.builder().code(Code.TOKEN_EXPIRED).build();
        }

    }

}
