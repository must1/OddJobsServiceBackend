package odd.jobs.services;

import odd.jobs.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserCrudOperationsService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, UserCrudOperationsService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public void register(User user) {
        User hashedUser = user.toBuilder()
                .username(user.getUsername().trim())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userService.save(hashedUser);
    }
}
