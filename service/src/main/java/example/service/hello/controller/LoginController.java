package example.service.hello.controller;

import example.service.hello.controller.model.LoginRequest;
import example.service.hello.controller.model.LoginResponse;
import example.service.hello.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    private final AuthService authService;

    private LoginController(AuthService authService) {
        this.authService = authService;
    }

    @MessageMapping("login")
    public Mono<LoginResponse> login(LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword())
                .map(jwt -> {
                    LoginResponse response = new LoginResponse();
                    response.setToken(jwt);

                    return response;
                });
    }

    @MessageMapping("logout")
    public Mono<?> logout(@Header("token") String token) {
        return authService.authorize(token)
                .map(authService::logout);
    }
}
