package odd.jobs.controllers;

import odd.jobs.entities.photo.Photo;
import odd.jobs.entities.user.User;
import odd.jobs.services.photo.PhotoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class PhotoController {
    private final PhotoCrudService photoService;

    @Autowired
    public PhotoController(PhotoCrudService photoService) {
        this.photoService = photoService;
    }

    @PatchMapping("/users/img")
    public boolean uploadImage(@AuthenticationPrincipal User reporter,
                               @RequestParam("image") MultipartFile imageFile) {
        photoService.saveFile(reporter, imageFile);
        return true;
    }

    @GetMapping("/users/img/{username}")
    public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable String username) {
        Photo photo = photoService.getUserPhoto(username).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + photo.getFileName() + "\"")
                .body(new ByteArrayResource(photo.getData()));
    }
}
