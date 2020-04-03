package odd.jobs.controllers;


import odd.jobs.entities.user.User;
import odd.jobs.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/register")
    public String renderRegister(@AuthenticationPrincipal User user){
        if(user != null){
            //TODO return homePage path
            return "redirect:/homePage";
        }
        //TODO return register path
        return "validation=false";
    }

    @PostMapping("/register")
    public List<String> registerUser(@RequestBody User user){
        //TODO return JSON
        return registrationService.register(user);
    }
}
