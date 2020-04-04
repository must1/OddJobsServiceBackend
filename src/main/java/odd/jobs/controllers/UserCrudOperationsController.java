package odd.jobs.controllers;

import odd.jobs.services.UserCrudOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCrudOperationsController {

    private final UserCrudOperationsService userCrudOperationsService;

    @Autowired
    public UserCrudOperationsController(UserCrudOperationsService userCrudOperationsService) {
        this.userCrudOperationsService = userCrudOperationsService;
    }

    @GetMapping("/users/{name}")
    public UserDetails getUserByName(@PathVariable String name) {
        return userCrudOperationsService.loadUserByUsername(name);
    }
}
