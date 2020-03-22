package odd.jobs.entities;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Value
@Builder
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
}
