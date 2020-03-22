package odd.jobs.controllers;

import odd.jobs.entities.User;
import odd.jobs.services.UserCrudOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getUserByName(@PathVariable String name) {
        return userCrudOperationsService.getUserByName(name);
    }
}
