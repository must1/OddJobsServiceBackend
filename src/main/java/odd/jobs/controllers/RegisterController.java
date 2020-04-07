package odd.jobs.controllers;


import odd.jobs.entities.user.User;
import odd.jobs.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RegisterController {

    private final RegistrationService registrationService;

    @Autowired
    public RegisterController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public List<String> registerUser(@RequestBody User user){
        //TODO return JSON
        return registrationService.register(user);
    }
}
