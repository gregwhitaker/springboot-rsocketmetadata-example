package example.service.hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Map;

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
    public Mono<String> hello(String name, @Headers Map<String, Object> metadata) {
        return Mono.fromSupplier(() -> String.format("Hello, %s! [metadata1: '%s', metadata2: '%s']", name, metadata.get("metadata1"), metadata.get("metadata2")));
    }
}
