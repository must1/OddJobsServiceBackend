package odd.jobs.services.validator.components;

import odd.jobs.entities.user.User;

public class PasswordValidator implements IUserAttributesValidator {

    @Override
    public String validate(User user) {
        String attribute = user.getPassword();
        if(attribute.length() > 30){
            return "password is too long";
        } else if(attribute.length() < 3) {
            return "password is too short";
        } else if(attribute.contains(" ")){
            return "password contains illegal character";
        }
        return null;
    }
}
