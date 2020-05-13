package odd.jobs.services.advertisement;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.AdvertisementCategory;
import odd.jobs.entities.user.Role;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.AdvertisementRepository;
import odd.jobs.repositories.UserRepository;
import odd.jobs.services.advertisement.validator.SaveAdvertisementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementCrudService {

    private final AdvertisementRepository advertisementRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AdvertisementCrudService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public Advertisement loadById(long Id) {
        Optional<Advertisement> advertisement = advertisementRepository.findById(Id);
        return advertisement.orElse(new Advertisement());
    }

    public List<String> saveAdvertisement(Advertisement advertisement, User requester) {
        if (requester == null || requester.isBlocked()) {
            return Collections.singletonList("you need to log in to create advertisement");
        }
        SaveAdvertisementValidator validator = new SaveAdvertisementValidator();
        List<String> messages = validator.validate(advertisement);
        if (messages.isEmpty()) {
            advertisementRepository.save(advertisement.toBuilder()
                    .dateTime(LocalDateTime.now())
                    .createdBy(requester.getUsername())
                    .build());
        }
        return messages;

    }

    public List<Advertisement> getAdvertisements(String city, AdvertisementCategory advertisementCategory) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Advertisement> query = cb.createQuery(Advertisement.class);
        Root<Advertisement> table = query.from(Advertisement.class);

        if (city != null) {
            predicates.add(cb.equal(table.get("city"), city));
        }
        if (advertisementCategory != null) {
            predicates.add(cb.equal(table.get("advertisementCategory"), advertisementCategory));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));
        final TypedQuery<Advertisement> result = entityManager.createQuery(query);

        return result.getResultList();
    }
}
