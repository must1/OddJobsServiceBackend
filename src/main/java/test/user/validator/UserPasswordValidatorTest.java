package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.checkers.UserPasswordValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserPasswordValidatorTest {
    UserPasswordValidator userPasswordValidator;
    User user;


    @BeforeAll
    void validatorInit() {
        userPasswordValidator = new UserPasswordValidator();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    private String passwordGenerator(int length) {
        Random random = new Random();
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Test
    void testIfResponseIsPropertyWhenPasswordIsCorrect() {
        int length = 10;
        String password = passwordGenerator(length);
        user = user.toBuilder()
                .password(password)
                .build();
        Assertions.assertNull(userPasswordValidator.validate(user));
    }

    @Test
    void testIfResponseIsPropertyWhenPasswordIsTooShort() {
        int length = 2;
        String password = passwordGenerator(length);
        user = user.toBuilder()
                .password(password)
                .build();
        Assertions.assertEquals(userPasswordValidator.validate(user), "password is too short");
    }

    @Test
    void testIfResponseIsPropertyWhenPasswordIsTooLong() {
        int length = 100;
        String password = passwordGenerator(length);
        user = user.toBuilder()
                .password(password)
                .build();
        Assertions.assertEquals(userPasswordValidator.validate(user), "password is too long");
    }

    @Test
    void testIfResponseIsPropertyWhenPasswordHasSpaceCharacter() {
        int length = 20;
        String password = passwordGenerator(length) + " ";
        user = user.toBuilder()
                .password(password)
                .build();
        Assertions.assertEquals(userPasswordValidator.validate(user), "password contains illegal character");
    }
}