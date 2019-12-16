package example.service.hello.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import example.service.hello.core.auth.TokenAuthorizer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Service that handles authentication with the system.
 */
@Component
public class AuthService {

    private TokenAuthorizer tokenAuthorizer;

    public AuthService(TokenAuthorizer tokenAuthorizer) {
        this.tokenAuthorizer = tokenAuthorizer;
    }

    /**
     * Check that the token is authorized to access the system.
     *
     * @param token jwt token to check
     * @return a {@link DecodedJWT} if successful
     */
    public Mono<DecodedJWT> authorize(String token) {
        return tokenAuthorizer.authorize(token);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public Mono<String> login(String username, String password) {
        return null;
    }

    /**
     *
     * @param decodedJWT
     * @return
     */
    public Mono<?> logout(DecodedJWT decodedJWT) {
        return null;
    }
}
