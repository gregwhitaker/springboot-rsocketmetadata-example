package example.service.hello.controller;

import example.service.hello.service.AuthService;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * Controller that handles hello world messages.
 */
@Controller
public class HelloController {

    private final AuthService authService;

    public HelloController(AuthService authService) {
        this.authService = authService;
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
        return authService.authorize(token)
                .map(decodedJWT -> String.format("Hello, %s, from %s!", name, decodedJWT.getClaim("username")));
    }
}
