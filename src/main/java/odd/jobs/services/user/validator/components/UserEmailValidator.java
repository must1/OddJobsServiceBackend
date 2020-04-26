package odd.jobs.services.user.validator.components;

import odd.jobs.entities.user.User;

public class UserEmailValidator implements IUserAttributesValidator {
    @Override
    public String validate(User user) {
        String attribute = user.getEmail();
        if(!attribute.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            return "incorrect email address";
        }
        return null;
    }

}
