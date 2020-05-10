package odd.jobs.services.user.availability;

import odd.jobs.entities.user.User;
import odd.jobs.repositories.UserRepository;
import odd.jobs.services.user.availability.components.IUserAvailabilityChecker;
import odd.jobs.services.user.availability.components.UserEmailChecker;
import odd.jobs.services.user.availability.components.UserPhoneChecker;
import odd.jobs.services.user.availability.components.UsernameChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserAvailabilityChecker {

    final private List<IUserAvailabilityChecker> validators;

    final private UserRepository userRepository;

    public UserAvailabilityChecker(UserRepository userRepository){
        this.userRepository = userRepository;
        validators = new ArrayList<>();
        validators.add(new UserEmailChecker());
        validators.add(new UsernameChecker());
        validators.add(new UserPhoneChecker());
    }

    public List<String> check(User user){

        return validators.stream()
                .map(e -> e.check(user, userRepository))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
