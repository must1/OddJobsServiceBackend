package odd.jobs.services.user.validator.checkers;

import odd.jobs.entities.user.User;

public interface IUserAttributesValidator {

    String validate(User user);
}
