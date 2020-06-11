package odd.jobs.controllers;

import javassist.NotFoundException;
import odd.jobs.services.advertisement.FeatureAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureAdvertisementController {

    private final FeatureAdvertisementService featureAdvertisementService;

    @Autowired
    public FeatureAdvertisementController(FeatureAdvertisementService featureAdvertisementService) {
        this.featureAdvertisementService = featureAdvertisementService;
    }

    @PutMapping("/advertisements/{id}")
    public void feature(@PathVariable long id) throws NotFoundException {
        featureAdvertisementService.feature(id);
    }

    @PatchMapping("/advertisements/{id}")
    public void unfeature(@PathVariable long id) throws NotFoundException {
        featureAdvertisementService.unfeature(id);
    }
}
