package odd.jobs.controllers;


import odd.jobs.entities.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {

    @GetMapping("/homePage")
    @ResponseBody //TODO Delete it later (when URL's will be known)
    public String renderHomePage(@AuthenticationPrincipal UserEntity user){
        if(user != null){
            //TODO return homePage path
            return "homePage";
        }
        //TODO return homePage path
        return "redirect:/login";
    }
}
