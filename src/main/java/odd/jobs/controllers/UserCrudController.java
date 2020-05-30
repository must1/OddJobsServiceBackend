package odd.jobs.controllers;

import javassist.NotFoundException;
import odd.jobs.entities.user.User;
import odd.jobs.services.photo.PhotoCrudService;
import odd.jobs.services.user.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class UserCrudController {

    private final UserCrudService userService;
    private final PhotoCrudService photoService;

    @Autowired
    public UserCrudController(UserCrudService userService, PhotoCrudService photoService) {
        this.userService = userService;
        this.photoService = photoService;
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
}
