package example.service.hello.controller;

import example.service.hello.controller.model.LoginRequest;
import example.service.hello.controller.model.LoginResponse;
import example.service.hello.service.AuthService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * Controller that handles authenticating users.
 */
@Controller
public class LoginController {

    private final AuthService authService;

    private LoginController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Checks the username and password with the system. If correct, this
     * method issues a JWT token for authenticating with the service.
     *
     * @param request username and password
     * @return a {@link LoginResponse} if the login was successful
     */
    @MessageMapping("login")
    public Mono<LoginResponse> login(LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword())
                .map(jwt -> {
                    LoginResponse response = new LoginResponse();
                    response.setToken(jwt);

                    return response;
                });
    }
}
