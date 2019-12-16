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

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.UUID;

@Component
public class TokenUtil {
    private static final Logger LOG = LoggerFactory.getLogger(TokenUtil.class);

    @Value("${hello.jwt.secret}")
    private String jwtSecret;

    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public TokenUtil() {
        this.algorithm = Algorithm.HMAC256(jwtSecret);
        this.verifier = JWT.require(algorithm)
                .withIssuer("springboot-rsocketmetadata-example")
                .build();
    }

    /**
     *
     * @param token
     * @return
     */
    public Mono<DecodedJWT> isValid(String token) {
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

    /**
     *
     * @param username
     * @return
     */
    public Mono<String> create(String username) {
        return Mono.fromSupplier(() -> JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withIssuer("springboot-rsocketmetadata-example")
                .withIssuedAt(Date.from(Instant.now()))
                .withSubject(username)
                .withExpiresAt(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
                .sign(algorithm));
    }
}
