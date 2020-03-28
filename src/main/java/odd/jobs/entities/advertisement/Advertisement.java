package odd.jobs.entities.advertisement;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class Advertisement {

    @Id
    @GeneratedValue
    private long advertisementID;
    private String title;
    private String description;
    private AdvertisementCategory advertisementCategory;
    private LocalDateTime dateTime;
    private boolean featured;
    @OneToMany
    private List<AdvertisementPhoto> photos;
}
