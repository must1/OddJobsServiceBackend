package odd.jobs.controllers;


import odd.jobs.entities.UserEntity;
import odd.jobs.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register")
    public String renderRegister(@AuthenticationPrincipal UserEntity user){
        if(user != null){
            //TODO return homePage path
            return "redirect:/homePage";
        }
        //TODO return register path
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserEntity user){
        registrationService.register(user);
        //TODO return login path
        return "login";
    }
}
