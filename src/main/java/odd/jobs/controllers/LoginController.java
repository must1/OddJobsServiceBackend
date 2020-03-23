package odd.jobs.controllers;


import odd.jobs.entities.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    @ResponseBody //TODO Delete it later (when URL's will be known)
    public String renderLogin(@AuthenticationPrincipal UserEntity user, @RequestParam(required=false) boolean error){
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
