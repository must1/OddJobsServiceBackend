package odd.jobs.services.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserPasswordEncoder {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String code(String password){
        return passwordEncoder.encode(password);
    }
}
