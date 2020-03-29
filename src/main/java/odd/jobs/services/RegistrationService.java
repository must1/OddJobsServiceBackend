package odd.jobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserCrudOperationsService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserCrudOperationsService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(NotValidatedUser user) {
        if(user.isCorrect()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user.validateToUser());
        }
    }
}
