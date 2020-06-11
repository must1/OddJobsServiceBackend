package odd.jobs.services.user.availability.checkers;

import javassist.NotFoundException;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.UserRepository;

public class UsernameChecker implements IUserAvailabilityChecker {

    @Override
    public String check(User user, UserRepository userRepository) {
        try{
            userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new NotFoundException("User not found"));
        }
        catch (NotFoundException ex){
            return null;
        }
        return "User with given username already exists";
    }
}
