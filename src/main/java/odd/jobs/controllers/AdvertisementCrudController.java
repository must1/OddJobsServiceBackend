package odd.jobs.controllers;

import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.AdvertisementCategory;
import odd.jobs.entities.user.User;
import odd.jobs.services.advertisement.AdvertisementCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertisementCrudController {

    private final AdvertisementCrudService advertisementService;

    @Autowired
    public AdvertisementCrudController(AdvertisementCrudService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/advertisements/{id}")
    public Advertisement getAdvertisementById(@PathVariable long id) {
        return advertisementService.loadById(id);
    }

    @PostMapping("/advertisements")
    public List<String> saveAdvertisement(@RequestBody Advertisement advertisement,
                                          @AuthenticationPrincipal User requester){
        return advertisementService.saveAdvertisement(advertisement, requester);
    }

    @GetMapping("/advertisements")
    public List<Advertisement> getAdvertisements(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "advertisementCategory", required = false) AdvertisementCategory advertisementCategory) {
        return advertisementService.getAdvertisements(city, advertisementCategory);
    }

}
