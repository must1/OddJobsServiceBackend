package test.advertisement.validator;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.services.advertisement.validator.components.AdvertisementDescriptionValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdvertisementDescriptionValidatorTest {
    AdvertisementDescriptionValidator advertisementDescriptionValidator;
    Advertisement advertisement;
    Random random;

    @BeforeAll
    void validatorInit() {
        advertisementDescriptionValidator = new AdvertisementDescriptionValidator();
        random = new Random();
    }

    @BeforeEach
    void advertisementInit() {
        advertisement = new Advertisement();
    }

    private String descriptionGenerator(int length) {
        return random.ints(97, 122)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Test
    void validDescription() {
        int length = random.nextInt(244) + 10;
        String description = descriptionGenerator(length);
        advertisement = advertisement.toBuilder().description(description).build();
        Assertions.assertNull(advertisementDescriptionValidator.validate(advertisement));
    }

    @Test
    void tooLongDescriptionIsInvalid() {
        int length = 300;
        String description = descriptionGenerator(length);
        advertisement = advertisement.toBuilder().description(description).build();
        Assertions.assertEquals(advertisementDescriptionValidator.validate(advertisement), "description is too long");
    }

    @Test
    void descriptionWithIllegalCharactersIsInvalid() {
        int length = random.nextInt(100) + 10;
        String description = descriptionGenerator(length) + "/";
        advertisement = advertisement.toBuilder().description(description).build();
        Assertions.assertEquals(advertisementDescriptionValidator.validate(advertisement), "description contains illegal character");
    }
}