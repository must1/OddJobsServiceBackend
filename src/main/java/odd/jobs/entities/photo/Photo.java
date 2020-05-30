package odd.jobs.entities.photo;


import lombok.*;

import javax.persistence.*;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Table(name="photos")
public class Photo {

    @Id
    @GeneratedValue
    private long photoId;
    private String fileName;
    private String type;

    @Lob
    private byte[] data;
}
