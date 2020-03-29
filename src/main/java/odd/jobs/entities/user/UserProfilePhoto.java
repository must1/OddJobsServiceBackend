package odd.jobs.entities.user;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class UserProfilePhoto {

    @Id
    @GeneratedValue
    private long idUserProfilePhoto;
    private String path;
    @ManyToOne
    private User user;
}

