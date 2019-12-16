package example.service.hello.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import example.service.hello.core.auth.TokenAuthorizer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthService {

    private TokenAuthorizer tokenAuthorizer;

    public AuthService(TokenAuthorizer tokenAuthorizer) {
        this.tokenAuthorizer = tokenAuthorizer;
    }

    public Mono<DecodedJWT> authorize(String token) {
        return tokenAuthorizer.authorize(token);
    }

    public Mono<String> login(String username, String password) {
        return null;
    }

    public Mono<?> logout(DecodedJWT decodedJWT) {
        return null;
    }
}
