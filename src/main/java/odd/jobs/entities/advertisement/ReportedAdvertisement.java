package odd.jobs.entities.advertisement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import odd.jobs.entities.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReportedAdvertisement {

    @Id
    @GeneratedValue
    private long idReportedAdvertisement;
    @ManyToOne
    private User user;
    @ManyToOne
    private Advertisement advertisement;
    private String description;
}
