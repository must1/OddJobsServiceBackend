package odd.jobs.services.user.availability.checkers;

import javassist.NotFoundException;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.UserRepository;

public class UserEmailChecker implements IUserAvailabilityChecker {

    @Override
    public String check(User user, UserRepository userRepository) {
        try{
            userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new NotFoundException("User not found"));
        }
        catch (NotFoundException ex){
            return null;
        }
        return "User with given email already exists";
    }
}
