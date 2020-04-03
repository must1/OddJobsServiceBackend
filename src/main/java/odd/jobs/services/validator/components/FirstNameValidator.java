package odd.jobs.services.validator.components;

import odd.jobs.entities.user.User;

public class FirstNameValidator implements IValidator {

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
