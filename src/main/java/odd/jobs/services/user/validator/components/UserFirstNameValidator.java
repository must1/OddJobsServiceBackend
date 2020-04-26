package odd.jobs.services.user.validator.components;

import odd.jobs.entities.user.User;

public class UserFirstNameValidator implements IUserAttributesValidator {

    @Override
    public String validate(User user) {
        String attribute = user.getFirstName();
        if(attribute.length() > 30){
            return "firstName is too long";
        } else if(attribute.length() < 3) {
            return "firstName is too short";
        } else if(!attribute.matches("[a-zA-Z]+")){
            return "firstName contains illegal character";
        }
        return null;
    }
}
