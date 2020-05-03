package odd.jobs.services.user.validator.components;

import odd.jobs.entities.user.User;

public class UserPasswordValidator implements IUserAttributesValidator {

    @Override
    public String validate(User user) {
        String attribute = user.getPassword();
        if(attribute.length() > 71){
            return "password is too long";
        } else if(attribute.length() < 3) {
            return "password is too short";
        } else if(attribute.contains(" ")){
            return "password contains illegal character";
        }
        return null;
    }
}
