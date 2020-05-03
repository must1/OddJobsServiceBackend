package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.components.UserPasswordValidator;
import odd.jobs.services.user.validator.components.UsernameValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserPasswordValidatorTest {
    UserPasswordValidator userPasswordValidator;
    User user;
    Random random;

    @BeforeAll
    void validatorInit() {
        userPasswordValidator = new UserPasswordValidator();
        random = new Random();
    }

    private String passwordGenerator(int length)
    {
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    @Test
    void validPassword()
    {
        int length = random.nextInt(25) + 3;
        String password = passwordGenerator(length);
        user=user.toBuilder().password(password).build();
        Assertions.assertNull(userPasswordValidator.validate(user));
    }

    @Test
    void tooShortpasswordIsInvalid()
    {
        int length = 2;
        String password = passwordGenerator(length);
        user=user.toBuilder().password(password).build();
        Assertions.assertEquals(userPasswordValidator.validate(user),"password is too short");
    }

    @Test
    void tooLongPasswordIsInvalid()
    {
        int length = 40;
        String password = passwordGenerator(length);
        user=user.toBuilder().password(password).build();
        Assertions.assertEquals(userPasswordValidator.validate(user),"password is too long");
    }

    @Test
    void passwordIncludingSpaceIsInvalid()
    {
        int length = 20;
        String password = passwordGenerator(length)+" ";
        user=user.toBuilder().password(password).build();
        Assertions.assertEquals(userPasswordValidator.validate(user),"password contains illegal character");
    }
}