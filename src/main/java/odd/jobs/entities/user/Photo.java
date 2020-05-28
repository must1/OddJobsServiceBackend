package odd.jobs.entities.user;


import javax.persistence.*;

@Entity
@Table(name="photos")
public class Photo {

    @Id
    @GeneratedValue
    private int photoId;
    private String path;
    private String fileName;
}
