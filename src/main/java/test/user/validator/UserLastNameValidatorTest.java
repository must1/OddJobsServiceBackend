package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.checkers.UserLastNameValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserLastNameValidatorTest {
    UserLastNameValidator userLastNameValidator;
    User user;


    @BeforeAll
    void validatorInit() {
        userLastNameValidator = new UserLastNameValidator();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    private String lastNameGenerator(int length) {
        Random random = new Random();
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Test
    void testIfResponseIsPropertyWhenLastNameIsCorrect() {
        int length = 10;
        String lastName = lastNameGenerator(length);
        user = user.toBuilder()
                .lastName(lastName)
                .build();
        Assertions.assertNull(userLastNameValidator.validate(user));
    }

    @Test
    void testIfResponseIsPropertyWhenLastNameIsTooShort() {
        int length = 2;
        String lastName = lastNameGenerator(length);
        user = user.toBuilder()
                .lastName(lastName)
                .build();
        Assertions.assertEquals(userLastNameValidator.validate(user), "lastName is too short");
    }

    @Test
    void testIfResponseIsPropertyWhenLastNameIsTooLong() {
        int length = 40;
        String lastName = lastNameGenerator(length);
        user = user.toBuilder()
                .lastName(lastName)
                .build();
        Assertions.assertEquals(userLastNameValidator.validate(user), "lastName is too long");
    }

    @Test
    void testIfResponseIsPropertyWhenLastNameHasIllegarCharacter() {
        int length = 20;
        String lastName = lastNameGenerator(length) + "/";
        user = user.toBuilder()
                .lastName(lastName)
                .build();
        Assertions.assertEquals(userLastNameValidator.validate(user), "lastName contains illegal character");
    }
}