package odd.jobs.services.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.validator.components.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserRegisterValidator {

    private List<IValidator> validators;

    public UserRegisterValidator(){
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new FirstNameValidator());
        validators.add(new LastNameValidator());
        validators.add(new PasswordValidator());
        validators.add(new PhoneValidator());
        validators.add(new UsernameValidator());
    }

    public List<String> validate(User user){

        return validators.stream()
                .map(e -> e.validate(user))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
