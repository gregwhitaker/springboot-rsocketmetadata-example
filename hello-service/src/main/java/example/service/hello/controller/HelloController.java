package example.service.hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * Controller that generates hello messages.
 */
@Controller
public class HelloController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    /**
     * Return a hello message.
     *
     * @param name name to put in the hello message
     * @return hello message
     */
    @MessageMapping("hello")
    public Mono<String> hello(String name) {
        return Mono.just(String.format("Hello, %s!", name));
    }
}
