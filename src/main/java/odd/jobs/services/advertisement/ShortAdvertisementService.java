package odd.jobs.services.advertisement;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.ShortAdvertisement;
import odd.jobs.repositories.AdvertisementRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShortAdvertisementService {

    private static final int MAXIMUM_CHARACTERS_FOR_DESCRIPTION = 40;
    private static final int MAXIMUM_CHARACTERS_FOR_TITLE = 10;
    private final AdvertisementRepository advertisementRepository;

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
}
