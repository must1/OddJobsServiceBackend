package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.components.UsernameValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsernameValidatorTest {
    UsernameValidator UsernameValidator;
    User user;
    Random random;

    @BeforeAll
    void validatorInit() {
        UsernameValidator = new UsernameValidator();
        random=new Random();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    private String usernameGenerator(int length)
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
        String username = usernameGenerator(length);
        user=user.toBuilder().username(username).build();
        Assertions.assertNull(UsernameValidator.validate(user));
    }

    @Test
    void tooShortUsernameIsInvalid()
    {
        int length = 2;
        String username = usernameGenerator(length);
        user=user.toBuilder().username(username).build();
        Assertions.assertEquals(UsernameValidator.validate(user),"username is too short");
    }

    @Test
    void tooLongUsernameIsInvalid()
    {
        int length = 40;
        String username = usernameGenerator(length);
        user=user.toBuilder().username(username).build();
        Assertions.assertEquals(UsernameValidator.validate(user),"username is too long");
    }

    @Test
    void usernameWithIllegalSignIsInvalid()
    {
        int length = 20;
        String username = usernameGenerator(length)+"/";
        user=user.toBuilder().username(username).build();
        Assertions.assertEquals(UsernameValidator.validate(user),"username contains illegal character");
    }
}