package odd.jobs.controllers;

import javassist.NotFoundException;
import odd.jobs.entities.advertisement.ReportedAdvertisement;
import odd.jobs.entities.user.User;
import odd.jobs.services.advertisement.ReportedAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportedAdvertisementController {

    private final ReportedAdvertisementService reportedAdvertisementService;

    @Autowired
    public ReportedAdvertisementController(ReportedAdvertisementService reportedAdvertisementService) {
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
    public List<String> report(
            @AuthenticationPrincipal User requester,
            @RequestBody ReportedAdvertisement reportedAdvertisement) throws NotFoundException {
        return reportedAdvertisementService.report(requester, reportedAdvertisement);
    }

    @PatchMapping("/reported/{id}")
    public String acceptReport(@PathVariable long id,
                               @AuthenticationPrincipal User requester) throws NotFoundException {
        return reportedAdvertisementService.acceptReport(id, requester);
    }

    @DeleteMapping("/reported/{id}")
    public String rejectReport(@PathVariable long id,
                               @AuthenticationPrincipal User requester) throws NotFoundException {
        return reportedAdvertisementService.rejectReport(id, requester);
    }

}
