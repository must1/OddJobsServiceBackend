package odd.jobs.services.advertisement;

import javassist.NotFoundException;
import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.ReportedAdvertisement;
import odd.jobs.entities.user.userEnum.Role;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.AdvertisementRepository;
import odd.jobs.repositories.ReportedAdvertisementRepository;
import odd.jobs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReportedAdvertisementService {

    private final ReportedAdvertisementRepository reportedAdvertisementRepository;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportedAdvertisementService(ReportedAdvertisementRepository reportedAdvertisementRepository, AdvertisementRepository advertisementRepository, UserRepository userRepository) {
        this.reportedAdvertisementRepository = reportedAdvertisementRepository;
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
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

    @Transactional
    public List<String> report(User requester, ReportedAdvertisement reportedAdvertisement) throws NotFoundException {
        if (requester == null) {
            return Collections.singletonList("you need to log in to create advertisement");
        }
        if (requester.isBlocked()) {
            return Collections.singletonList("you are blocked");
        }
        if (reportedAdvertisement.getDescription().length() > 255) {
            return Collections.singletonList("description is too long");
        } else if (!reportedAdvertisement.getDescription().matches("^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$")) {
            return Collections.singletonList("description contains illegal character");
        }
        ReportedAdvertisement repAdvertisement = new ReportedAdvertisement();
        Advertisement advertisement = advertisementRepository.findById(reportedAdvertisement.getAdvertisement().getAdvertisementID())
                .orElseThrow(() -> new NotFoundException("Advertisement with id " + reportedAdvertisement.getAdvertisement().getAdvertisementID() + " not found"));

        repAdvertisement = repAdvertisement.toBuilder()
                .advertisement(advertisement)
                .user(requester)
                .description((reportedAdvertisement.getDescription())).build();
        repAdvertisement.setAdvertisement(advertisement);
        repAdvertisement.setUser(requester);
        reportedAdvertisementRepository.save(repAdvertisement);
        return Collections.emptyList();
    }

    public String acceptReport(long id, User requester) throws NotFoundException {
        if (requester == null || requester.isBlocked()) {
            return "you need to log in to create advertisement";
        }
        if (requester.getRole() != Role.ADMIN) {
            return "you need to become an admin";
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
        if (requester.getRole() != Role.ADMIN) {
            return "you need to become an admin";
        }
        ReportedAdvertisement reportedAdvertisement = reportedAdvertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reported advertisement not found"));
        reportedAdvertisementRepository.delete(reportedAdvertisement);
        return "";
    }
}
