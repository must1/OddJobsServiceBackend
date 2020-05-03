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
    Random random;

    @BeforeAll
    void validatorInit() {
        userFirstNameValidator = new UserFirstNameValidator();
        random = new Random();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    private String firstNameGenerator(int length)
    {
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Test
    void validFirstName()
    {
        int length = random.nextInt(25) + 3;
        String firstName = firstNameGenerator(length);
        user=user.toBuilder().firstName(firstName).build();
        Assertions.assertNull(userFirstNameValidator.validate(user));
    }

    @Test
    void tooShortFirstNameIsInvalid()
    {
        int length = 2;
        String firstName = firstNameGenerator(length);
        user=user.toBuilder().firstName(firstName).build();
        Assertions.assertEquals(userFirstNameValidator.validate(user),"firstName is too short");
    }

    @Test
    void tooLongFirstNameIsInvalid()
    {
        int length = 40;
        String firstName = firstNameGenerator(length);
        user=user.toBuilder().firstName(firstName).build();
        Assertions.assertEquals(userFirstNameValidator.validate(user),"firstName is too long");
    }

    @Test
    void firstNameWithIllegalSignIsInvalid()
    {
        int length = 20;
        String firstName = firstNameGenerator(length)+"/";
        user=user.toBuilder().firstName(firstName).build();
        Assertions.assertEquals(userFirstNameValidator.validate(user),"firstName contains illegal character");
    }
}