package odd.jobs.repositories;

import odd.jobs.entities.advertisement.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

  /*  @Query("select a from Book a where  ")
    List<Advertisement> fetchXAdvetisements(@Param("number") int number);*/

/*    @Query("SELECT a FROM Advertisement WHERE a.category=:category")
    List<Advertisement> fetchXAdvetisementsForGivenCategory(@Param("category") String category);*/
}
