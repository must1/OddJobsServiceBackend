package odd.jobs.controllers;

import javassist.NotFoundException;
import odd.jobs.entities.user.User;
import odd.jobs.services.user.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserCrudController {

    private final UserCrudService userService;

    @Autowired
    public UserCrudController(UserCrudService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{name}")
    public UserDetails getUserByName(@PathVariable String name) {
        return userService.loadUserByUsername(name);
    }

    @PostMapping("/users")
    public List<String> registerUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PatchMapping("/users")
    public List<String> updateUser(@Valid @RequestBody User user,
                           @AuthenticationPrincipal User requester) throws NotFoundException {
        return userService.updateUser(user, requester);
    }

    @PatchMapping("/users/{username}")
    public boolean blockUser(@PathVariable String username,
                             @AuthenticationPrincipal User reporter) throws NotFoundException {
        return userService.blockUser(username, reporter);
    }

    @PatchMapping("/users/img")
    public boolean uploadImage(@AuthenticationPrincipal User reporter,
                              @RequestParam("image") MultipartFile imageFile) throws Exception {
        return userService.saveUserImg(reporter, imageFile);
    }
}
