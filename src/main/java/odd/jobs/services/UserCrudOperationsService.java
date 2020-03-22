package odd.jobs.services;

import odd.jobs.entities.User;
import odd.jobs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCrudOperationsService {

    private final UserRepository userRepository;

    @Autowired
    public UserCrudOperationsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
}
