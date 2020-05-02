package odd.jobs.controllers;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.AdvertisementCategory;
import odd.jobs.entities.advertisement.ShortAdvertisement;
import odd.jobs.services.advertisement.ShortAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShortAdvertisementController {

    private final ShortAdvertisementService shortAdvertisementService;

    @Autowired
    public ShortAdvertisementController(ShortAdvertisementService shortAdvertisementService) {
        this.shortAdvertisementService = shortAdvertisementService;
    }

    @GetMapping("/search/advertisements/{number}")
    public List<ShortAdvertisement> getGeneralShortAdvertisements(@PathVariable("number") int number) {
        return shortAdvertisementService.getGeneralShortAdvertisements(number);
    }

    @GetMapping("/search/advertisements")
    public List<Advertisement> getSpecicShortAdvertisements(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "category", required = false) AdvertisementCategory category) {
        return shortAdvertisementService.getSpecificShortAdvertisements(city,category);
    }
}
