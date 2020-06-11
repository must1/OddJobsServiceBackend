package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.checkers.UsernameValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsernameValidatorTest {
    UsernameValidator UsernameValidator;
    User user;

    @BeforeAll
    void validatorInit() {
        UsernameValidator = new UsernameValidator();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    private String usernameGenerator(int length) {
        Random random = new Random();
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Test
    void testIfResponseIsPropertyWhenUsernameIsCorrect() {
        int length = 10;
        String username = usernameGenerator(length);
        user = user.toBuilder()
                .username(username)
                .build();
        Assertions.assertNull(UsernameValidator.validate(user));
    }

    @Test
    void testIfResponseIsPropertyWhenUsernameIsTooShort() {
        int length = 2;
        String username = usernameGenerator(length);
        user = user.toBuilder()
                .username(username)
                .build();
        Assertions.assertEquals(UsernameValidator.validate(user), "username is too short");
    }

    @Test
    void testIfResponseIsPropertyWhenUsernameIsTooLong() {
        int length = 40;
        String username = usernameGenerator(length);
        user = user.toBuilder()
                .username(username)
                .build();
        Assertions.assertEquals(UsernameValidator.validate(user), "username is too long");
    }

    @Test
    void testIfResponseIsPropertyWhenUsernameHasIllegalCharacter() {
        int length = 20;
        String username = usernameGenerator(length) + "/";
        user = user.toBuilder()
                .username(username)
                .build();
        Assertions.assertEquals(UsernameValidator.validate(user), "username contains illegal character");
    }
}