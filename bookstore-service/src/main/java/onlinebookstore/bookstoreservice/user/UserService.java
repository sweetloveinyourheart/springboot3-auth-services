package onlinebookstore.bookstoreservice.user;

import onlinebookstore.bookstoreservice.common.exceptions.custom.UnAuthorizedException;
import onlinebookstore.bookstoreservice.user.dtos.UserDTO;
import onlinebookstore.bookstoreservice.user.entities.Profile;
import onlinebookstore.bookstoreservice.user.entities.User;
import onlinebookstore.bookstoreservice.user.enums.UserRole;
import onlinebookstore.bookstoreservice.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createNewUser(UserDTO user) {
        // create new profile
        Profile newProfile = new Profile();
        newProfile.setFullName(user.getProfile().getFullName());
        newProfile.setPhone(user.getProfile().getPhone());
        newProfile.setAddress(user.getProfile().getAddress());
        newProfile.setBirthday(user.getProfile().getBirthday());

        // hash password
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());

        // create new user
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(hashedPassword);
        newUser.setProfile(newProfile);
        newUser.setRole(UserRole.CUSTOMER);

        userRepository.save(newUser);

        return user;
    }

    public User getUserByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UnAuthorizedException("User not found");
        }

        return user.get();
    }
}
