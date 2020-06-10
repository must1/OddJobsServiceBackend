package odd.jobs.services.advertisement;

import javassist.NotFoundException;
import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.advertisementEnum.AdvertisementCategory;
import odd.jobs.entities.advertisement.advertisementEnum.City;
import odd.jobs.entities.advertisement.advertisementEnum.ContractType;
import odd.jobs.entities.advertisement.advertisementEnum.WorkingHours;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.AdvertisementRepository;
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
        if (requester == null) {
            return Collections.singletonList("you need to log in to create advertisement");
        }
        if (requester.isBlocked()) {
            return Collections.singletonList("you are blocked");
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

    public List<Advertisement> getAdvertisements(City city, AdvertisementCategory advertisementCategory, ContractType contractType, WorkingHours workingHours, String createdBy) {
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
        if (contractType != null) {
            predicates.add(cb.equal(table.get("contractType"), contractType));
        }
        if (workingHours != null) {
            predicates.add(cb.equal(table.get("workingHours"), workingHours));
        }
        if (createdBy != null) {
            predicates.add(cb.equal(table.get("createdBy"), createdBy));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));
        final TypedQuery<Advertisement> result = entityManager.createQuery(query);

        return result.getResultList();
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
    public boolean deleteAdvertisement(long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElse(null);
        advertisementRepository.deleteById(id);
        return true;
    }
}
