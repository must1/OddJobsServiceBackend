package test.advertisement.validator;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.AdvertisementCategory;
import odd.jobs.services.advertisement.validator.components.AdvertisementCategoryValidator;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdvertisementCategoryValidatorTest {
    AdvertisementCategoryValidator advertisementCategoryValidator;
    Advertisement advertisement;

    @BeforeAll
    void validatorInit() {
        advertisementCategoryValidator = new AdvertisementCategoryValidator();
    }

    @BeforeEach
    void advertisementInit() {
        advertisement = new Advertisement();
    }

    @Test
    void testIfResponseIsPropertyWhenCategoryIsCorrect() {
        advertisement = new Advertisement();
        for (AdvertisementCategory category : AdvertisementCategory.values()) {
            advertisement = advertisement.toBuilder()
                    .advertisementCategory(category)
                    .build();
            Assertions.assertNull(advertisementCategoryValidator.validate(advertisement));
        }
    }
    //TODO: Advertisement, which will be rejected by this validator
}