package odd.jobs.services.advertisement.validator;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.services.advertisement.validator.components.AdvertisementCategoryValidator;
import odd.jobs.services.advertisement.validator.components.AdvertisementDescriptionValidator;
import odd.jobs.services.advertisement.validator.components.AdvertisementTitleValidator;
import odd.jobs.services.advertisement.validator.components.IAdvertisementAttributesValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SaveAdvertisementValidator {

    final private List<IAdvertisementAttributesValidator> validators;

    public SaveAdvertisementValidator() {
        validators = new ArrayList<>();
        validators.add(new AdvertisementCategoryValidator());
        validators.add(new AdvertisementDescriptionValidator());
        validators.add(new AdvertisementTitleValidator());
    }

    public List<String> validate(Advertisement advertisement) {

        return validators.stream()
                .map(e -> e.validate(advertisement))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
