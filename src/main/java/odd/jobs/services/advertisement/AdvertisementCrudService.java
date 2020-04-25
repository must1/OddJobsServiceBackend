package odd.jobs.services.advertisement;

import javassist.NotFoundException;
import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.repositories.AdvertisementRepository;
import odd.jobs.services.advertisement.validator.SaveAdvertisementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementCrudService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementCrudService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public Advertisement loadById(long Id) {
        Optional<Advertisement> advertisement = advertisementRepository.findById(Id);
        return advertisement.orElse(new Advertisement());
    }

    public List<String> saveAdvertisement(Advertisement advertisement){
        SaveAdvertisementValidator validator = new SaveAdvertisementValidator();
        List<String> messages = validator.validate(advertisement);
        if(messages.isEmpty()){
            advertisementRepository.save(advertisement.toBuilder()
            .dateTime(LocalDateTime.now())
            .build());
        }
        return messages;
    }
}
