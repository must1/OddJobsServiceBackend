package test.user.validator;


import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.SaveUserValidator;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserValidatorTest {
    SaveUserValidator validator;
    User user;

    @BeforeAll
    void validatorInit() {
        validator = new SaveUserValidator();
    }

    @BeforeEach
    void advertisementInit() {
        user = new User();
    }

    @Test
    void testIfResponseIsPropertyWhenUserIsCorrect() {
        user=user.toBuilder()
                .phoneNumber("111222333")
                .password("czerwonebiedronki123!")
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan_kowalski@gmail.com")
                .username("JanKowalski123")
                .build();
        Assertions.assertTrue(validator.validate(user).isEmpty());
    }

    @Test
    void testIfResponseIsPropertyWhenUserIsIncorrect() {
        user=user.toBuilder()
                .phoneNumber("111222333444")//phoneNumber must have 9 digits
                .password("czerwonebiedronki123!")
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jan_kowalski@gmail.com")
                .username("JanKowalski123")
                .build();
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }
}