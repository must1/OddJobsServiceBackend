package odd.jobs.services;


import lombok.*;
import odd.jobs.entities.user.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NotValidatedUser {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int phoneNumber;

    public User validateToUser(){
        return User.builder()
                .password(password)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }

    public boolean isCorrect(){
        try{
            isValidEmailAddress();
            isPasswordValid();
            isPhoneValid();
            areNamesValid();
        }catch(IllegalArgumentException ex){
            return false;
        }
        return true;
    }

    private void areNamesValid() throws IllegalArgumentException{
        List<String> names = Arrays.asList(firstName, lastName, username);
        for (String name: names) {
            if(name.length() > 20 || name.length() < 3) throw new IllegalArgumentException();
            if(!name.matches("[A-Za-z0-9]+")) throw new IllegalArgumentException();
        }
    }

    private void isValidEmailAddress() throws IllegalArgumentException {
        if(!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) throw new IllegalArgumentException();
    }

    private void isPasswordValid() throws IllegalArgumentException{
        if(password.length() > 20 || password.length() < 3) throw new IllegalArgumentException();
    }

    private void isPhoneValid() throws IllegalArgumentException{
        if(String.valueOf(phoneNumber).length() != 9) throw new IllegalArgumentException();
    }

}
