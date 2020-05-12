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
import java.util.List;

@Service
public class ReportedAdvertisementOperationsService {

    private final ReportedAdvertisementRepository reportedAdvertisementRepository;
    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;


    @Autowired
    public ReportedAdvertisementOperationsService(ReportedAdvertisementRepository reportedAdvertisementRepository, UserRepository userRepository, AdvertisementRepository advertisementRepository) {
        this.reportedAdvertisementRepository = reportedAdvertisementRepository;
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    public List<ReportedAdvertisement> getAllReportedAdvertisement() {
        List<ReportedAdvertisement> temp = new ArrayList<>();
        reportedAdvertisementRepository.findAll().forEach(temp::add);
        return temp;
    }

    public ReportedAdvertisement getReportedAdvertisement(Long id) throws NotFoundException {
        List<ReportedAdvertisement> temp = new ArrayList<>();
        ReportedAdvertisement reportedAdvertisement = reportedAdvertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reported advertisement not found"));
        return reportedAdvertisement;
    }

    public void report(long userId, long advertisementId, String description) throws NotFoundException {
        ReportedAdvertisement reportedAdvertisement = new ReportedAdvertisement();
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> new NotFoundException("Advertisement with id " + advertisementId + " not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));
        reportedAdvertisement = reportedAdvertisement.toBuilder()
                .advertisement(advertisement)
                .user(user)
                .description(description).build();
        reportedAdvertisementRepository.save(reportedAdvertisement);
    }

    public void acceptReport(long id) throws NotFoundException {
        ReportedAdvertisement reportedAdvertisement = reportedAdvertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reported advertisement not found"));
        Advertisement advertisement = reportedAdvertisement.getAdvertisement();
        reportedAdvertisementRepository.delete(reportedAdvertisement);
        advertisementRepository.delete(advertisement);
    }

    public void rejectReport(long id) throws NotFoundException {
        ReportedAdvertisement reportedAdvertisement = reportedAdvertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reported advertisement not found"));
        reportedAdvertisementRepository.delete(reportedAdvertisement);
    }
}
