package odd.jobs.services.advertisement;

import javassist.NotFoundException;
import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.ReportedAdvertisement;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.AdvertisementRepository;
import odd.jobs.repositories.ReportedAdvertisementRepository;
import odd.jobs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReportedAdvertisementService {

    private final ReportedAdvertisementRepository reportedAdvertisementRepository;
    private final AdvertisementRepository advertisementRepository;


    @Autowired
    public ReportedAdvertisementService(ReportedAdvertisementRepository reportedAdvertisementRepository, AdvertisementRepository advertisementRepository) {
        this.reportedAdvertisementRepository = reportedAdvertisementRepository;
        this.advertisementRepository = advertisementRepository;
    }

    public List<ReportedAdvertisement> getAllReportedAdvertisement() {
        List<ReportedAdvertisement> temp = new ArrayList<>();
        reportedAdvertisementRepository.findAll().forEach(temp::add);
        return temp;
    }

    public ReportedAdvertisement getReportedAdvertisement(Long id) throws NotFoundException {
        return reportedAdvertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reported advertisement not found"));
    }

    public List<String> report(User requester, long advertisementId, String description) throws NotFoundException {
        if (requester == null || requester.isBlocked()) {
            return Collections.singletonList("you need to log in to create advertisement");
        }
        if (description.length() > 255) {
            return Collections.singletonList("description is too long");
        } else if (!description.matches("^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$")) {
            return Collections.singletonList("description contains illegal character");
        }
        ReportedAdvertisement reportedAdvertisement = new ReportedAdvertisement();
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> new NotFoundException("Advertisement with id " + advertisementId + " not found"));
        reportedAdvertisement = reportedAdvertisement.toBuilder()
                .advertisement(advertisement)
                .user(requester)
                .description(description).build();
        reportedAdvertisementRepository.save(reportedAdvertisement);
        return Collections.emptyList();
    }

    public String acceptReport(long id, User requester) throws NotFoundException {
        if (requester == null || requester.isBlocked()) {
            return "you need to log in to create advertisement";
        }
        ReportedAdvertisement reportedAdvertisement = reportedAdvertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reported advertisement not found"));
        Advertisement advertisement = reportedAdvertisement.getAdvertisement();
        reportedAdvertisementRepository.delete(reportedAdvertisement);
        advertisementRepository.delete(advertisement);
        return "";
    }

    public String rejectReport(long id, User requester) throws NotFoundException {
        if (requester == null || requester.isBlocked()) {
            return "you need to log in to create advertisement";
        }
        ReportedAdvertisement reportedAdvertisement = reportedAdvertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reported advertisement not found"));
        reportedAdvertisementRepository.delete(reportedAdvertisement);
        return "";
    }
}
