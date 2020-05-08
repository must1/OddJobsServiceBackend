/*
package odd.jobs.services.advertisement;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.AdvertisementCategory;
import odd.jobs.entities.advertisement.ShortAdvertisement;
import odd.jobs.repositories.AdvertisementRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.criterion.Restrictions.conjunction;

@Service
public class ShortAdvertisementService {

    private static final int MAXIMUM_CHARACTERS_FOR_DESCRIPTION = 40;
    private static final int MAXIMUM_CHARACTERS_FOR_TITLE = 10;
    private final AdvertisementRepository advertisementRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ShortAdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public List<ShortAdvertisement> getGeneralShortAdvertisements(int number) {
        Page<Advertisement> generalAdvertisements = advertisementRepository.findAll(
                PageRequest.of(0, number));

        return generalAdvertisements.stream().map(iteratedAdvertisement -> ShortAdvertisement
                .builder()
                .id(iteratedAdvertisement.getAdvertisementID())
                .category(iteratedAdvertisement.getAdvertisementCategory())
                .description(shortenText(iteratedAdvertisement.getDescription(), MAXIMUM_CHARACTERS_FOR_DESCRIPTION))
                .title(shortenText(iteratedAdvertisement.getTitle(), MAXIMUM_CHARACTERS_FOR_TITLE))
                .build())
                .collect(Collectors.toList());
    }

    private String shortenText(String description, int maximumCharacters) {
        return StringUtils.abbreviate(description, maximumCharacters);
    }

    public List<Advertisement> a(String city, AdvertisementCategory advertisementCategory) {
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
*/
