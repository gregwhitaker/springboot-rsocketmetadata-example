package example.client.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class HelloClientApplication {
    private static final Logger LOG = LoggerFactory.getLogger(HelloClientApplication.class);

    public static void main(String... args) {
        SpringApplication.run(HelloClientApplication.class, args);
    }

    /**
     * Runs the application.
     */
    @Component
    public class Runner implements CommandLineRunner {

        @Autowired
        private RSocketRequester rSocketRequester;

        @Override
        public void run(String... args) throws Exception {

        }
    }
}
