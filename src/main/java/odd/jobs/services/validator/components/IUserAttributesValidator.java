package odd.jobs.services.validator.components;

import odd.jobs.entities.user.User;

public interface IUserAttributesValidator {

    String validate(User user);
}
