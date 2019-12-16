package example.service.hello.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import example.service.hello.core.auth.PasswordUtil;
import example.service.hello.core.auth.TokenUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Service that handles authentication with the system.
 */
@Component
public class AuthService {
    private final Map<String, String> USERS = new HashMap<>();

    private TokenUtil tokenUtil;

    public AuthService(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;

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
        return tokenUtil.isValid(token)
                .map(decodedJWT -> {
                    if (decodedJWT.getExpiresAt().after(Date.from(Instant.now()))) {
                        return decodedJWT;
                    } else {
                        throw new RuntimeException("JWT token has expired");
                    }
                });
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public Mono<String> login(String username, String password) {
        return Mono.fromSupplier(() -> USERS.containsKey(username) && PasswordUtil.matches(password, USERS.get(username))).flatMap(valid -> {
            if (valid) {
                return tokenUtil.create(username);
            } else {
                return Mono.error(new RuntimeException("Invalid username or password"));
            }
        });
    }
}
