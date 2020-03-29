package odd.jobs.entities.advertisement;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class AdvertisementPhoto {

    @Id
    @GeneratedValue
    private long idAdvertisementPhoto;
    @ManyToOne
    private Advertisement advertisement;
    private String path;
}
