package test.advertisement.validator;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.services.advertisement.validator.checkers.AdvertisementTitleValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdvertisementTitleValidatorTest {

    private AdvertisementTitleValidator advertisementTitleValidator;
    private Advertisement advertisement;

    @BeforeAll
    void validatorInit() {
        advertisementTitleValidator = new AdvertisementTitleValidator();
    }

    @BeforeEach
    void advertisementInit() {
        advertisement = new Advertisement();
    }

    private String titleGenerator(int length) {
        Random random = new Random();
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Test
    void testIfResponseIsPropertyWhenTitleIsCorrect() {
        int length = 20;
        String title = titleGenerator(length);
        advertisement = advertisement.toBuilder()
                .title(title)
                .build();
        Assertions.assertNull(advertisementTitleValidator.validate(advertisement));
    }

    @Test
    void testIfResponseIsPropertyWhenTitleIsTooLong() {
        int length = 100;
        String title = titleGenerator(length);
        advertisement = advertisement.toBuilder().title(title).build();
        Assertions.assertEquals(advertisementTitleValidator.validate(advertisement), "title is too long");
    }

    @Test
    void testIfResponseIsPropertyWhenTitleIsTooShort() {
        int length = 10;
        String title = titleGenerator(length);
        advertisement = advertisement.toBuilder()
                .title(title)
                .build();
        Assertions.assertEquals(advertisementTitleValidator.validate(advertisement), "title is too short");
    }

    @Test
    void testIfResponseIsPropertyWhenTitleHasIllegalCharacter() {
        int length = 20;
        String title = titleGenerator(length) + "/";
        advertisement = advertisement.toBuilder()
                .title(title)
                .build();
        Assertions.assertEquals(advertisementTitleValidator.validate(advertisement), "title contains illegal character");
    }

}