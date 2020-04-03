package odd.jobs.services;


import odd.jobs.entities.user.User;
import odd.jobs.services.validator.UserRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final UserCrudOperationsService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserCrudOperationsService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<String> register(User user) {
        UserRegisterValidator validator = new UserRegisterValidator();
        List<String> messages = validator.validate(user);
        if(messages.isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
        }
        return messages;
    }
}
