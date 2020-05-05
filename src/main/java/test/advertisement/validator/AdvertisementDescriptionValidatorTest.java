package test.advertisement.validator;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.services.advertisement.validator.components.AdvertisementDescriptionValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdvertisementDescriptionValidatorTest {
    AdvertisementDescriptionValidator advertisementDescriptionValidator;
    Advertisement advertisement;

    @BeforeAll
    void validatorInit() {
        advertisementDescriptionValidator = new AdvertisementDescriptionValidator();
    }

    @BeforeEach
    void advertisementInit() {
        advertisement = new Advertisement();
    }

    private String descriptionGenerator(int length) {
        Random random=new Random();
        return random.ints(97, 122)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Test
    void testIfResponseIsPropertyWhenDescriptionIsCorrect() {
        int length = 20;
        String description = descriptionGenerator(length);
        advertisement = advertisement.toBuilder().description(description).build();
        Assertions.assertNull(advertisementDescriptionValidator.validate(advertisement));
    }

    @Test
    void testIfResponseIsPropertyWhenDescriptionIsTooLong() {
        int length = 300;
        String description = descriptionGenerator(length);
        advertisement = advertisement.toBuilder().description(description).build();
        Assertions.assertEquals(advertisementDescriptionValidator.validate(advertisement), "description is too long");
    }

    @Test
    void testIfResponseIsPropertyWhenDescriptionIsTooShort() {
        int length = 20;
        String description = descriptionGenerator(length) + "/";
        advertisement = advertisement.toBuilder().description(description).build();
        Assertions.assertEquals(advertisementDescriptionValidator.validate(advertisement), "description contains illegal character");
    }
}