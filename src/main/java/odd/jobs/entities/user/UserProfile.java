package odd.jobs.entities.user;

import lombok.Getter;

import javax.persistence.*;
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
