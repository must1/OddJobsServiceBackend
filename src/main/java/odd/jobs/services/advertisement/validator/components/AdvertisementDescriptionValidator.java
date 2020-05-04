package odd.jobs.services.advertisement.validator.components;

import odd.jobs.entities.advertisement.Advertisement;

public class AdvertisementDescriptionValidator implements IAdvertisementAttributesValidator {
    @Override
    public String validate(Advertisement advertisement) {
        String attribute = advertisement.getDescription();
        if(attribute.length() > 255){
            return "description is too long";
        } else if(!attribute.matches("^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$")){
            return "description contains illegal character";
        }
        return null;
    }
}