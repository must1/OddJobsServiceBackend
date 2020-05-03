package test.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.components.UserPasswordValidator;
import odd.jobs.services.user.validator.components.UserPhoneValidator;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserPhoneValidatorTest {
    UserPhoneValidator userPhoneValidator;
    User user;
    Random random;

    @BeforeAll
    void validatorInit() {
        userPhoneValidator = new UserPhoneValidator();
        random = new Random();
    }

    @BeforeEach
    void userInit() {
        user = new User();
    }

    @Test
    void validPhoneNumber()
    {
        String phoneNumber = String.valueOf(Math.abs(random.nextLong())%899999999+100000000);
        user=user.toBuilder().phoneNumber(phoneNumber).build();
        Assertions.assertNull(userPhoneValidator.validate(user));
    }

    @Test
    void tooShortNumberIsInvalid()
    {
        int length = random.nextInt(6)+1;
        String phoneNumber = String.valueOf((random.nextLong()%899999999+100000000)).substring(0,length);
        user=user.toBuilder().phoneNumber(phoneNumber).build();
        Assertions.assertEquals(userPhoneValidator.validate(user),"phone number too short");
    }

    @Test
    void tooLongNumberIsInvalid()
    {
        int length = random.nextInt(6)+1;
        String phoneNumber = String.valueOf((random.nextLong()+1000000000));
        user=user.toBuilder().phoneNumber(phoneNumber).build();
        Assertions.assertEquals(userPhoneValidator.validate(user),"phone number too long");
    }
}