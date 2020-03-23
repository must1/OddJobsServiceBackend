package odd.jobs.services;

import odd.jobs.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserCrudOperationsService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UserEntity user) {
        user.setUsername(user.getUsername().trim());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
    }
}
