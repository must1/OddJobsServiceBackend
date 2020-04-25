package odd.jobs.services.user.validator.components;

import odd.jobs.entities.user.User;

public class UserPhoneValidator implements IUserAttributesValidator {

    @Override
    public String validate(User user) {
        String attribute = user.getPhoneNumber();
        if(attribute.length() > 9){
            return "phone number too long";
        }
        else if(attribute.length() < 9) {
            return "phone number too short";
        }
        else if(!attribute.matches("[0-9]+")){
            return "phone number must consist of numbers";
        }
        return null;
    }
}
