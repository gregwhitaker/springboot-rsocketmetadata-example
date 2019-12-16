package example.service.hello.controller.model;

/**
 * API response sent on successful login.
 */
public class LoginResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
