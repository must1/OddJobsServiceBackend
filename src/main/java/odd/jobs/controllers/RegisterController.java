package odd.jobs.controllers;


import odd.jobs.entities.UserEntity;
import odd.jobs.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register")
    @ResponseBody //TODO Delete it later (when URL's will be known)
    public String renderRegister(@AuthenticationPrincipal UserEntity user){
        if(user != null){
            //TODO return homePage path
            return "redirect:/homePage";
        }
        //TODO return register path
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody //TODO Delete it later (when URL's will be known)
    public String registerUser(@RequestBody UserEntity user){
        registrationService.register(user);
        //TODO return login path
        return "login";
    }
}
