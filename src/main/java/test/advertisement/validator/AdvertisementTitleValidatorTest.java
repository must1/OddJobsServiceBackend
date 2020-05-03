package test.advertisement.validator;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.services.advertisement.validator.components.AdvertisementTitleValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdvertisementTitleValidatorTest {
    AdvertisementTitleValidator advertisementTitleValidator;
    Advertisement advertisement;
    Random random;

    @BeforeAll
    void validatorInit() {
        advertisementTitleValidator = new AdvertisementTitleValidator();
        random = new Random();
    }

    @BeforeEach
    void advertisementInit() {
        advertisement = new Advertisement();
    }

    private String titleGenerator(int length) {
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Test
    void tooLongTitleIsInvalid() {
        int length = 100;
        String title = titleGenerator(length);
        advertisement = advertisement.toBuilder().title(title).build();
        Assertions.assertEquals(advertisementTitleValidator.validate(advertisement), "title is too long");
    }

    @Test
    void tooShortTitleIsInvalid() {
        int length = 10;
        String title = titleGenerator(length);
        advertisement = advertisement.toBuilder().title(title).build();
        Assertions.assertEquals(advertisementTitleValidator.validate(advertisement), "title is too short");
    }

    @Test
    void titleWithIllegalCharactersIsInvalid() {
        int length = random.nextInt(100) + 10;
        String title = titleGenerator(length) + "/";
        advertisement = advertisement.toBuilder().title(title).build();
        Assertions.assertEquals(advertisementTitleValidator.validate(advertisement), "title contains illegal character");
    }

}