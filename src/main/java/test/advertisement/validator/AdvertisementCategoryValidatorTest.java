package test.advertisement.validator;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.advertisementEnum.AdvertisementCategory;
import odd.jobs.services.advertisement.validator.checkers.AdvertisementCategoryValidator;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdvertisementCategoryValidatorTest {

    private AdvertisementCategoryValidator advertisementCategoryValidator;
    private Advertisement advertisement;

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
        advertisement = advertisement.toBuilder()
                .advertisementCategory(AdvertisementCategory.valueOf("GARDENCARE"))
                .build();
        Assertions.assertNull(advertisementCategoryValidator.validate(advertisement));
    }

    @Test
    void testIfExceptionIsThrownWhenAttemptingToAssignInvalidCategory() {
        advertisement = new Advertisement();
        Assertions.assertThrows(IllegalArgumentException.class, () -> advertisement = advertisement.toBuilder()
                .advertisementCategory(AdvertisementCategory.valueOf("NOTHING"))
                .build());
    }
}