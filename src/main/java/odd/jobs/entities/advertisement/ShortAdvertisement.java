package odd.jobs.entities.advertisement;

import lombok.Builder;
import lombok.Getter;
import odd.jobs.entities.advertisement.advertisementEnum.AdvertisementCategory;

@Getter
@Builder
public class ShortAdvertisement {

    private long id;
    private String title;
    private String description;
    private AdvertisementCategory category;
}
