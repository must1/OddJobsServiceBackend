package odd.jobs.services.advertisement;

import javassist.NotFoundException;
import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;

@Service
public class FeatureAdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public FeatureAdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public void feature(long id) throws NotFoundException {
        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Advertisement not found"));
        advertisement = advertisement.toBuilder()
                .featured(true)
                .build();
        advertisementRepository.save(advertisement);
    }

    public void unfeature(long id) throws NotFoundException {
        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Advertisement not found"));
        advertisement = advertisement.toBuilder()
                .featured(false)
                .build();
        advertisementRepository.save(advertisement);
    }

}
