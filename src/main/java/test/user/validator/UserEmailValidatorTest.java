package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.components.UserEmailValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserEmailValidatorTest {
    UserEmailValidator userEmailValidator;
    User user;
    Random random;

    @BeforeAll
    void validatorInit() {
        userEmailValidator = new UserEmailValidator();
        random=new Random();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    private String emailPartGenerator(int length)
    {
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    @Test
    void validEmail()
    {
        int length = 10;
        String email=emailPartGenerator(length)+"@"+emailPartGenerator(length);
        user=user.toBuilder().email(email).build();
        Assertions.assertNull(userEmailValidator.validate(user));
    }

    @Test
    void emailWithoutAtSignIsInvalid()
    {
        int length = 20;
        String email = emailPartGenerator(length);
        user=user.toBuilder().email(email).build();
        Assertions.assertEquals(userEmailValidator.validate(user),"incorrect email address");
    }
    @Test
    void emailWithIncorrectSignIsInvalid()
    {
        int length = 10;
        String email=emailPartGenerator(length)+"@"+emailPartGenerator(length)+"/";
        user=user.toBuilder().email(email).build();
        Assertions.assertEquals(userEmailValidator.validate(user),"incorrect email address");
    }



}