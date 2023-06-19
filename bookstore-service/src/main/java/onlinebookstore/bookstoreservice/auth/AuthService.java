package onlinebookstore.bookstoreservice.auth;

import onlinebookstore.bookstoreservice.auth.dtos.SignInRequest;
import onlinebookstore.bookstoreservice.auth.dtos.SignInResponse;
import onlinebookstore.bookstoreservice.auth.jwt.JwtService;
import onlinebookstore.bookstoreservice.common.exceptions.custom.NotFoundException;
import onlinebookstore.bookstoreservice.common.exceptions.custom.UnAuthorizedException;
import onlinebookstore.bookstoreservice.user.UserService;
import onlinebookstore.bookstoreservice.user.dtos.UserDTO;
import onlinebookstore.bookstoreservice.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthService(
            UserService userService,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserDTO register(UserDTO userDTO) {
        return userService.createNewUser(userDTO);
    }

    public SignInResponse signIn(SignInRequest account) {
        String email = account.getEmail();
        String password = account.getPassword();

        User user = userService.getUserByEmail(email);

        boolean isPasswordValid = passwordEncoder.matches(password, user.getPassword());

        if(!isPasswordValid) {
            throw new UnAuthorizedException("Unauthorized");
        }

        String accessToken = jwtService.generateToken(user);

        SignInResponse response = new SignInResponse();
        response.setAccessToken(accessToken);

        return response;
    }
}
