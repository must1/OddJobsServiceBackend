package odd.jobs.controllers;

import odd.jobs.entities.advertisement.ShortAdvertisement;
import odd.jobs.services.advertisement.AdvertisementFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShortAdvertisementController {

    private final AdvertisementFetcher advertisementFetcher;

    @Autowired
    public ShortAdvertisementController(AdvertisementFetcher advertisementFetcher) {
        this.advertisementFetcher = advertisementFetcher;
    }

    @GetMapping("/search/advertisements/{number}")
    public List<ShortAdvertisement> getGeneralShortAdvertisements(@PathVariable("number") int number) {
        return advertisementFetcher.getGeneralShortAdvertisements(number);
    }
}
