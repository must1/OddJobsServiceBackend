package odd.jobs.services.user;

import javassist.NotFoundException;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.UserRepository;
import odd.jobs.services.user.validator.SaveUserValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        List<String> messages = validator.validate(user);
        if(messages.isEmpty()){
            userRepository.save(user.toBuilder()
                    .password(passwordEncoder.encode(user.getPassword()))
                    .build());
        }
        return messages;
    }

    public List<String> updateUser(User update, UserDetails requester) throws NotFoundException {

        if((requester == null) || (!requester.getUsername().equals(update.getUsername()))){
            return Collections.singletonList("you cannot update not yours data");
        }

        SaveUserValidator validator = new SaveUserValidator();
        List<String> messages = validator.validate(update);
        if(messages.isEmpty()){
            User userToUpdate = userRepository.findByUsername(update.getUsername())
                    .orElseThrow(() -> new NotFoundException("User not found"));

            userRepository.save(userToUpdate.toBuilder()
                    .firstName(update.getFirstName())
                    .lastName(update.getLastName())
                    .password(passwordEncoder.encode(update.getPassword()))
                    .username(update.getUsername())
                    .email(update.getEmail())
                    .phoneNumber(update.getPhoneNumber())
                    .build());
        }
        return messages;
    }

    public String deleteUser(String username, UserDetails requester) throws NotFoundException {

        if(requester == null){
            return ("false");
        }
        User userToDelete = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if(!requester.getUsername().equals(userToDelete.getUsername())){
            return ("false");
        }
        userRepository.save(userToDelete.toBuilder()
                .isBlocked(true)
                .build());
        return("true");
    }
}
