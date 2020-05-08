/*
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
    public List<ShortAdvertisement> getAdvertisements(@PathVariable("number") int number) {
        return shortAdvertisementService.getAdvertisements(number);
    }
}
*/
