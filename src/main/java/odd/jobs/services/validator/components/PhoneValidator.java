package odd.jobs.services.validator.components;

import odd.jobs.entities.user.User;

public class PhoneValidator implements IUserAttributesValidator {

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
