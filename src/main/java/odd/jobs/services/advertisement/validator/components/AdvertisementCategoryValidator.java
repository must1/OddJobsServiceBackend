package odd.jobs.services.advertisement.validator.components;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.AdvertisementCategory;

public class AdvertisementCategoryValidator implements IAdvertisementAttributesValidator {
    @Override
    public String validate(Advertisement advertisement) {
        AdvertisementCategory attribute = advertisement.getAdvertisementCategory();
        for (AdvertisementCategory category : AdvertisementCategory.values()) {
            if (attribute.equals(category))
                return null;
        }
        return "category is wrong";
    }
}
