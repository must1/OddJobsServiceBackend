package odd.jobs.services;

import javassist.NotFoundException;

import odd.jobs.entities.user.User;
import odd.jobs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCrudOperationsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserCrudOperationsService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public User updateUser(long id, User user) throws NotFoundException {
        User updatedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        updatedUser = userRepository.save(updatedUser.toBuilder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .username(user.getUsername())
                .build());
        return updatedUser;
    }

    public void deleteUser(long id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
