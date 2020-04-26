package odd.jobs.services.user.validator.components;

import odd.jobs.entities.user.User;

public interface IUserAttributesValidator {

    String validate(User user);
}
