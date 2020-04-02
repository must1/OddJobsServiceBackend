package odd.jobs.entities.advertisement;

import lombok.Getter;
import odd.jobs.entities.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
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
