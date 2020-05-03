package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.components.UserFirstNameValidator;
import odd.jobs.services.user.validator.components.UserLastNameValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserLastNameValidatorTest {
    UserLastNameValidator userLastNameValidator;
    User user;
    Random random;

    @BeforeAll
    void validatorInit() {
        userLastNameValidator = new UserLastNameValidator();
        random=new Random();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }
    private String lastNameGenerator(int length)
    {
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    @Test
    void validLastName()
    {
        int length = random.nextInt(25) + 3;
        String lastName = lastNameGenerator(length);
        user=user.toBuilder().lastName(lastName).build();
        Assertions.assertNull(userLastNameValidator.validate(user));
    }

    @Test
    void tooShortLastNameIsInvalid()
    {
        int length = 2;
        String lastName = lastNameGenerator(length);
        user=user.toBuilder().lastName(lastName).build();
        Assertions.assertEquals(userLastNameValidator.validate(user),"lastName is too short");
    }

    @Test
    void tooLongLastNameIsInvalid()
    {
        int length = 40;
        String lastName = lastNameGenerator(length);
        user=user.toBuilder().lastName(lastName).build();
        Assertions.assertEquals(userLastNameValidator.validate(user),"lastName is too long");
    }

    @Test
    void lastNameWithIllegalSignIsInvalid()
    {
        int length = 20;
        String lastName = lastNameGenerator(length)+"/";
        user=user.toBuilder().lastName(lastName).build();
        Assertions.assertEquals(userLastNameValidator.validate(user),"lastName contains illegal character");
    }
}