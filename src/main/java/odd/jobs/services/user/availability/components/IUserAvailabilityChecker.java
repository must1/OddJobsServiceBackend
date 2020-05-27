package odd.jobs.services.user.availability.components;

import odd.jobs.entities.user.User;
import odd.jobs.repositories.UserRepository;

public interface IUserAvailabilityChecker {

    String check(User user, UserRepository userRepository);
}
