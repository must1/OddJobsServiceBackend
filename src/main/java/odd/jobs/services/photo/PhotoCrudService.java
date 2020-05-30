package odd.jobs.services.photo;

import odd.jobs.entities.photo.Photo;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.PhotoRepository;
import odd.jobs.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class PhotoCrudService {

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    public PhotoCrudService(UserRepository userRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    public void saveFile(User user, MultipartFile file) {
        try {
            Photo photo = photoRepository.save(Photo.builder()
                    .fileName(file.getOriginalFilename())
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .build());
            userRepository.save(user.toBuilder()
                    .photoId(photo.getPhotoId())
                    .build());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Photo> getUserPhoto(String username){
        return photoRepository.findById(userRepository.findByUsername(username)
                .get().getPhotoId());
    }
}
