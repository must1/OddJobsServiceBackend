package odd.jobs.services.user.validator;

import odd.jobs.entities.user.User;
import odd.jobs.services.user.validator.checkers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SaveUserValidator {

    final private List<IUserAttributesValidator> validators;

    public SaveUserValidator(){
        validators = new ArrayList<>();
        validators.add(new UserEmailValidator());
        validators.add(new UserFirstNameValidator());
        validators.add(new UserLastNameValidator());
        validators.add(new UserPasswordValidator());
        validators.add(new UserPhoneValidator());
        validators.add(new UsernameValidator());
    }

    public List<String> validate(User user){

        return validators.stream()
                .map(e -> e.validate(user))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
