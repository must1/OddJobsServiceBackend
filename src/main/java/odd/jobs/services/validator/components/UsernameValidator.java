package odd.jobs.services.validator.components;

import odd.jobs.entities.user.User;

public class UsernameValidator implements IValidator {

    @Override
    public String validate(User user) {
        String attribute = user.getUsername();
        if(attribute.length() > 30){
            return "username is too long";
        } else if(attribute.length() < 3) {
            return "username is too short";
        } else if(!attribute.matches("[A-Za-z0-9]+")){
            return "username contains illegal character";
        }
        return null;
    }
}
