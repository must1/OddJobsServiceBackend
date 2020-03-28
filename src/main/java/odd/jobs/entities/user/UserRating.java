package odd.jobs.entities.user;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class UserRating {

    @Id
    @GeneratedValue
    private long idUserRating;
    private String description;
    private int rating;
}
