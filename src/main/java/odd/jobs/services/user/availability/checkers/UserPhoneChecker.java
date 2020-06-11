package odd.jobs.services.user.availability.checkers;

import javassist.NotFoundException;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.UserRepository;

public class UserPhoneChecker implements IUserAvailabilityChecker {

    @Override
    public String check(User user, UserRepository userRepository) {
        try{
            userRepository.findByPhoneNumber(user.getPhoneNumber())
                    .orElseThrow(() -> new NotFoundException("User not found"));
        }
        catch (NotFoundException ex){
            return null;
        }
        return "User with given phone number already exists";
    }
}
