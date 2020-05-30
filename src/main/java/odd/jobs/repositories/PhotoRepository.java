package odd.jobs.repositories;

import odd.jobs.entities.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository  extends JpaRepository<Photo, Long> {

}