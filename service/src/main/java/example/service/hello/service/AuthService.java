package example.service.hello.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import example.service.hello.core.auth.TokenAuthorizer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that handles authentication with the system.
 */
@Component
public class AuthService {
    private final Map<String, String> USERS = new HashMap<>();

    private TokenAuthorizer tokenAuthorizer;

    public AuthService(TokenAuthorizer tokenAuthorizer) {
        this.tokenAuthorizer = tokenAuthorizer;

        // Setting up dummy username/password data
        USERS.put("admin", "$2a$10$YKo/iq9nslCRWiAbhn2twuoNQ5GQfoh.oamFYZucEUXOQsQl52Tm2");
        USERS.put("user", "$2a$10$J54vhPD4bVd78bPyz4.I1ec4EwkBdWjWbFrhOifooq1345nrdYT8");
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
