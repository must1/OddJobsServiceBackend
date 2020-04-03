package odd.jobs.entities.user;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
public class UserProfile {

    @Id
    @GeneratedValue
    private long idUserProfile;
    private String description;
    @OneToMany
    private List<UserRating> ratings;
}
