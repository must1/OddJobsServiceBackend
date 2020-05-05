package test.advertisement.validator;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.AdvertisementCategory;
import odd.jobs.services.advertisement.validator.SaveAdvertisementValidator;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdvertisementValidatorTest {
    SaveAdvertisementValidator validator;
    Advertisement advertisement;

    @BeforeAll
    void validatorInit() {
        validator = new SaveAdvertisementValidator();
    }

    @BeforeEach
    void advertisementInit() {
        advertisement = new Advertisement();
    }

    @Test
    void testIfResponseIsPropertyWhenAdvertisementIsCorrect() {
        advertisement=advertisement.toBuilder()
                .title("Lawn mowing in Krakow")
                .description("I am looking for a student willing to mow our lawn in Krakow on XYZ street for 100 PLN")
                .advertisementCategory(AdvertisementCategory.GARDENCARE)
                .build();
        Assertions.assertTrue(validator.validate(advertisement).isEmpty());
    }

    @Test
    void testIfResponseIsPropertyWhenAdvertisementIsIncorrect() {
        advertisement=advertisement.toBuilder()
                .title("Lawn mowing in Krakow.") //'.' is illegal character in title
                .description("I am looking for a student willing to mow our lawn in Krakow on XYZ street for 100 PLN")
                .advertisementCategory(AdvertisementCategory.GARDENCARE)
                .build();
        Assertions.assertFalse(validator.validate(advertisement).isEmpty());
    }
}