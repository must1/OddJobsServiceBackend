package odd.jobs.controllers;

import javassist.NotFoundException;
import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.services.advertisement.AdvertisementCrudService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Advertisement getAdvertisementById(@PathVariable long id) throws NotFoundException {
        return advertisementService.loadById(id);
    }

    @PostMapping("/advertisements")
    public List<String> saveAdvertisement(@RequestBody Advertisement advertisement){
        return advertisementService.saveAdvertisement(advertisement);
    }

}
