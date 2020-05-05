package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.components.UserEmailValidator;
import odd.jobs.services.user.validator.components.UserFirstNameValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserFirstNameValidatorTest {
    UserFirstNameValidator userFirstNameValidator;
    User user;

    @BeforeAll
    void validatorInit() {
        userFirstNameValidator = new UserFirstNameValidator();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    private String firstNameGenerator(int length) {
        Random random = new Random();
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Test
    void testIfResponseIsPropertyWhenFirstNameIsCorrect() {
        int length = 10;
        String firstName = firstNameGenerator(length);
        user = user.toBuilder()
                .firstName(firstName)
                .build();
        Assertions.assertNull(userFirstNameValidator.validate(user));
    }

    @Test
    void testIfResponseIsPropertyWhenFirstNameIsTooShort() {
        int length = 2;
        String firstName = firstNameGenerator(length);
        user = user.toBuilder()
                .firstName(firstName)
                .build();
        Assertions.assertEquals(userFirstNameValidator.validate(user), "firstName is too short");
    }

    @Test
    void testIfResponseIsPropertyWhenFirstNameIsTooLong() {
        int length = 40;
        String firstName = firstNameGenerator(length);
        user = user.toBuilder()
                .firstName(firstName)
                .build();
        Assertions.assertEquals(userFirstNameValidator.validate(user), "firstName is too long");
    }

    @Test
    void testIfResponseIsPropertyWhenFirstNameHasIllegalCharacter() {
        int length = 20;
        String firstName = firstNameGenerator(length) + "/";
        user = user.toBuilder()
                .firstName(firstName)
                .build();
        Assertions.assertEquals(userFirstNameValidator.validate(user), "firstName contains illegal character");
    }
}