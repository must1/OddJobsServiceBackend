package odd.jobs.services.user.validator.checkers;

import odd.jobs.entities.user.User;

public class UserLastNameValidator implements IUserAttributesValidator {

    @Override
    public String validate(User user) {
        String attribute = user.getLastName();
        if(attribute.length() > 30){
            return "lastName is too long";
        } else if(attribute.length() < 3) {
            return "lastName is too short";
        } else if(!attribute.matches("[a-zA-Z]+")){
            return "lastName contains illegal character";
        }
        return null;
    }
}
