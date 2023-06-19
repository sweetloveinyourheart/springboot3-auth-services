package onlinebookstore.bookstoreservice.auth;

import jakarta.validation.Valid;
import onlinebookstore.bookstoreservice.auth.dtos.SignInRequest;
import onlinebookstore.bookstoreservice.auth.dtos.SignInResponse;
import onlinebookstore.bookstoreservice.user.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest account) {
        return ResponseEntity.ok(authService.signIn(account));
    }
}
