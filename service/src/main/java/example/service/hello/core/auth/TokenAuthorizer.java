package example.service.hello.core.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TokenAuthorizer {
    private static final Logger LOG = LoggerFactory.getLogger(TokenAuthorizer.class);

    @Value("${hello.jwt.secret}")
    private String jwtSecret;

    private final JWTVerifier verifier;

    public TokenAuthorizer() {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        this.verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
    }

    /**
     *
     * @param token
     * @return
     */
    public Mono<DecodedJWT> authorize(String token) {
        return Mono.fromSupplier(() -> {
            try {
                LOG.debug("Authorizing token: {}", token);
                return this.verifier.verify(token);
            } catch (JWTVerificationException e){
                LOG.debug("Token not authorized: {}", token);
                throw new RuntimeException("Invalid token", e);
            }
        });
    }
}
