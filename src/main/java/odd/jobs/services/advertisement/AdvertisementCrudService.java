package odd.jobs.services.advertisement;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.AdvertisementRepository;
import odd.jobs.repositories.UserRepository;
import odd.jobs.services.advertisement.validator.SaveAdvertisementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementCrudService {

    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdvertisementCrudService(AdvertisementRepository advertisementRepository, UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }

    public List<Advertisement> getAllAdvertisements() {
        return new ArrayList<>(advertisementRepository.findAll());
    }

    public Advertisement loadById(long Id) {
        Optional<Advertisement> advertisement = advertisementRepository.findById(Id);
        return advertisement.orElse(new Advertisement());
    }

    public List<String> saveAdvertisement(Advertisement advertisement, long id) {
        User user = userRepository.findById(id).get(); // it has to exists so no need to check

        SaveAdvertisementValidator validator = new SaveAdvertisementValidator();
        List<String> messages = validator.validate(advertisement);
        if (messages.isEmpty()) {
            advertisementRepository.save(advertisement.toBuilder()
                    .dateTime(LocalDateTime.now())
                    .createdBy(user.getUsername())
                    .build());
        }
        return messages;
    }
}
