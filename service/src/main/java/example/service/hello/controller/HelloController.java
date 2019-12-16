package example.service.hello.controller;

import example.service.hello.core.auth.TokenAuthorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * Controller that handles hello world messages.
 */
@Controller
public class HelloController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    private final TokenAuthorizer tokenAuthorizer;

    public HelloController(TokenAuthorizer tokenAuthorizer) {
        this.tokenAuthorizer = tokenAuthorizer;
    }

    /**
     * Get a hello message for a name.
     *
     * @param name name for which to generate a message
     * @param token auth token
     * @return hello message if authorized; otherwise an exception
     */
    @MessageMapping(value = "hello")
    public Mono<String> hello(String name, @Header(name = "token") String token) {
        return tokenAuthorizer.authorize(token)
                .map(decodedJWT -> String.format("Hello, %s, from %s!", name, decodedJWT.getClaim("username")));
    }
}
