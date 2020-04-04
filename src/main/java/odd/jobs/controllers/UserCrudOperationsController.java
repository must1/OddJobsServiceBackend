package odd.jobs.controllers;

import javassist.NotFoundException;
import odd.jobs.entities.user.User;
import odd.jobs.services.UserCrudOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserCrudOperationsController {

    private final UserCrudOperationsService userCrudOperationsService;

    @Autowired
    public UserCrudOperationsController(UserCrudOperationsService userCrudOperationsService) {
        this.userCrudOperationsService = userCrudOperationsService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userCrudOperationsService.getAllUsers();
    }


    @GetMapping("/users/{id}")
    public UserDetails getUserById(@PathVariable long id) throws NotFoundException {
        return userCrudOperationsService.getUserById(id);
    }
    /*
    @GetMapping("/users/{name}")
    public UserDetails getUserByName(@PathVariable String name) {
        return userCrudOperationsService.loadUserByUsername(name);
    }
    */

    @PostMapping(value="/users", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@Valid @RequestBody User user) {
        return userCrudOperationsService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) throws NotFoundException {
        return userCrudOperationsService.updateUser(id, user);
    }
    @DeleteMapping(value="/users/{id}")
    public void deleteUser(@PathVariable long id) throws NotFoundException {
        userCrudOperationsService.deleteUser(id);
    }
}
