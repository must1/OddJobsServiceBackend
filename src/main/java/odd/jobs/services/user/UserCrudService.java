package odd.jobs.services.user;

import javassist.NotFoundException;
import odd.jobs.controllers.Role;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.UserRepository;
import odd.jobs.services.user.availability.UserAvailabilityChecker;
import odd.jobs.services.user.validator.SaveUserValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserCrudService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCrudService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
    }

    public List<User> getAllUsers() {
        List<User> temp = new ArrayList<>();
        userRepository.findAll().forEach(temp::add);
        return temp;
    }

    public List<String> saveUser(User user) {
        SaveUserValidator validator = new SaveUserValidator();
        UserAvailabilityChecker checker = new UserAvailabilityChecker(userRepository);
        List<String> messages = validator.validate(user);
        messages.addAll(checker.check(user));
        if (messages.isEmpty()) {
            userRepository.save(user.toBuilder()
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role(Role.User)
                    .build());
        }
        return messages;
    }

    public List<String> updateUser(User update, User requester) throws NotFoundException {

        if ((requester == null) || ((!requester.getUsername().equals(update.getUsername())) &&
                !(requester.getRole().equals(Role.Admin)))) {
            return Collections.singletonList("you cannot update not yours data");
        }
        User userToUpdate = userRepository.findByUsername(update.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        User userToSave = new NullAttributesUpdateFiller(userToUpdate, update).update();

        SaveUserValidator validator = new SaveUserValidator();
        List<String> messages = validator.validate(userToSave);
        if (messages.isEmpty()) {
            userRepository.save(userToSave);
        }
        return messages;
    }

    public boolean blockUser(String username, User reporter) throws NotFoundException {

        if (reporter == null) {
            return false;
        }
        User userToBlock = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!reporter.getUsername().equals(userToBlock.getUsername())) {
            return false;
        }
        userRepository.save(userToBlock.toBuilder()
                .isBlocked(true)
                .build());
        return true;
    }
}
