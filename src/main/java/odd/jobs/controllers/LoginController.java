package odd.jobs.controllers;


import odd.jobs.entities.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String renderLogin(@AuthenticationPrincipal User user, @RequestParam(required=false) boolean error){
        if(user != null){
            //TODO return homePage path
            return "redirect:/homePage";
        }
        if(error){
            //TODO return login path
            return "/login?error=true";
        }
        //TODO return login path
        return "login";
    }
}
