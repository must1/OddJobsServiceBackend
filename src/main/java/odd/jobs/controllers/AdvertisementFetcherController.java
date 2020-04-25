package odd.jobs.controllers;

import odd.jobs.entities.advertisement.ShortAdvertisement;
import odd.jobs.services.advertisement.ShortAdvertisementFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdvertisementFetcherController {

    private final ShortAdvertisementFetcher shortAdvertisementFetcher;

    @Autowired
    public AdvertisementFetcherController(ShortAdvertisementFetcher shortAdvertisementFetcher) {
        this.shortAdvertisementFetcher = shortAdvertisementFetcher;
    }

    @GetMapping("/search/advertisements/{number}")
    public List<ShortAdvertisement> getGeneralShortAdvertisements(@PathVariable("number") int number) {
        return shortAdvertisementFetcher.getGeneralShortAdvertisements(number);
    }
}
