package odd.jobs.entities.advertisement;

import lombok.*;
import odd.jobs.entities.user.User;

import javax.persistence.*;

@Getter
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ReportedAdvertisement {

    @Id
    @GeneratedValue
    private long idReportedAdvertisement;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private User user;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Advertisement advertisement;
    private String description;
}
