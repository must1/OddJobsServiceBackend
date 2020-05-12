package odd.jobs.repositories;

import odd.jobs.entities.advertisement.ReportedAdvertisement;
import org.springframework.data.repository.CrudRepository;

public interface ReportedAdvertisementRepository extends CrudRepository<ReportedAdvertisement,Long> {
}
