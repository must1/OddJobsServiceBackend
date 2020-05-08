package odd.jobs.services.advertisement.validator.components;

import odd.jobs.entities.advertisement.Advertisement;

public class AdvertisementTitleValidator implements IAdvertisementAttributesValidator {

    @Override
    public String validate(Advertisement advertisement) {
        String attribute = advertisement.getTitle();
        if (attribute.length() > 70) {
            return "title is too long";
        } else if (attribute.length() < 13) {
            return "title is too short";
        } else if (!attribute.matches("^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$")) {
            return "title contains illegal character";
        }
        return null;
    }
}