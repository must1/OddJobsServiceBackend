package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.checkers.UserPhoneValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserPhoneValidatorTest {
    UserPhoneValidator userPhoneValidator;
    User user;

    @BeforeAll
    void validatorInit() {
        userPhoneValidator = new UserPhoneValidator();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    private String phoneGenerator(int length) {
        Random random = new Random();
        long minNumber = (long) Math.pow(10, length - 1);
        long maxNumber = (long) Math.pow(10, length) - 1;
        return String.valueOf(Math.abs(random.nextLong()) % (maxNumber - minNumber) + minNumber);
    }

    @Test
    void testIfResponseIsPropertyWhenPhoneNumberIsCorrect() {
        int length = 9;
        String phoneNumber = phoneGenerator(length);
        user = user.toBuilder()
                .phoneNumber(phoneNumber)
                .build();
        Assertions.assertNull(userPhoneValidator.validate(user));
    }

    @Test
    void testIfResponseIsPropertyWhenPhoneNumberIsTooShort() {
        int length = 8;
        String phoneNumber = phoneGenerator(length);
        user = user.toBuilder()
                .phoneNumber(phoneNumber)
                .build();
        Assertions.assertEquals(userPhoneValidator.validate(user), "phone number too short");
    }

    @Test
    void testIfResponseIsPropertyWhenPhoneNumberIsTooLong() {
        int length = 10;
        String phoneNumber = phoneGenerator(length);
        user = user.toBuilder()
                .phoneNumber(phoneNumber)
                .build();
        Assertions.assertEquals(userPhoneValidator.validate(user), "phone number too long");
    }

    @Test
    void testIfResponseIsPropertyWhenPhoneNumberContainsNonDigitCharacter() {
        int length = 8;
        String phoneNumber = phoneGenerator(length);
        user = user.toBuilder()
                .phoneNumber(phoneNumber + "a")
                .build();
        Assertions.assertEquals(userPhoneValidator.validate(user), "phone number must consist of numbers");
    }
}