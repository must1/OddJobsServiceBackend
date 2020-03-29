package odd.jobs.controllers;


import odd.jobs.entities.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @GetMapping("/homePage")
    public String renderHomePage(@AuthenticationPrincipal User user){
        if(user != null){
            //TODO return homePage path
            return "homePage";
        }
        //TODO return homePage path
        return "redirect:/login";
    }
}
