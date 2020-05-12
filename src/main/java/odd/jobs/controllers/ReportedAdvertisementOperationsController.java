package odd.jobs.controllers;

import javassist.NotFoundException;
import odd.jobs.entities.advertisement.ReportedAdvertisement;
import odd.jobs.services.advertisement.ReportedAdvertisementOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportedAdvertisementOperationsController {

    private final ReportedAdvertisementOperationsService reportedAdvertisementService;

    @Autowired
    public ReportedAdvertisementOperationsController(ReportedAdvertisementOperationsService reportedAdvertisementService) {
        this.reportedAdvertisementService = reportedAdvertisementService;
    }

    @GetMapping("/reported")
    public List<ReportedAdvertisement> getAllReportedAdvertisement() {
        return reportedAdvertisementService.getAllReportedAdvertisement();
    }

    @GetMapping("/reported/{id}")
    public ReportedAdvertisement getReportedAdvertisement(@PathVariable long id) throws NotFoundException {
        return reportedAdvertisementService.getReportedAdvertisement(id);
    }

    @PostMapping("/reported")
    public void report(
            @RequestParam(value = "userId") long userId,
            @RequestParam(value = "advertisementId") long advertisementId,
            @RequestParam(value = "description") String description) throws NotFoundException {
        reportedAdvertisementService.report(userId, advertisementId, description);
    }

    @PatchMapping("/reported/{id}")
    public void acceptReport(@PathVariable long id) throws NotFoundException {
        reportedAdvertisementService.acceptReport(id);
    }

    @DeleteMapping("/reported/{id}")
    public void rejectReport(@PathVariable long id) throws NotFoundException {
        reportedAdvertisementService.rejectReport(id);
    }

}
