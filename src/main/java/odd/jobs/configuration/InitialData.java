package odd.jobs.configuration;

import lombok.extern.slf4j.Slf4j;
import odd.jobs.entities.advertisement.ReportedAdvertisement;
import odd.jobs.entities.advertisement.advertisementEnum.City;
import odd.jobs.entities.advertisement.advertisementEnum.ContractType;
import odd.jobs.entities.advertisement.advertisementEnum.WorkingHours;
import odd.jobs.entities.user.Role;
import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.advertisementEnum.AdvertisementCategory;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.AdvertisementRepository;
import odd.jobs.repositories.ReportedAdvertisementRepository;
import odd.jobs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@Slf4j
public class InitialData {

    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;
    private final ReportedAdvertisementRepository reportedAdvertisementRepository;

    @Autowired
    public InitialData(UserRepository userRepository, AdvertisementRepository advertisementRepository, ReportedAdvertisementRepository reportedAdvertisementRepository) {
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
        this.reportedAdvertisementRepository = reportedAdvertisementRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addUsersToDB() {
        log.info("Persisted account data to database");

        userRepository.save(User.builder()
                .firstName("Macko")
                .lastName("Bogdaniec")
                .username("must1")
                .email("analnyPenetrator6969@gmail.com")
                .password("$2a$10$3g4oIfNqX51bvq7pICs1ReHex8tfb3Dp3eJ9U.MvrX.aPXF7folb6")//123
                .phoneNumber("213702137")
                .role(Role.USER)
                .photo("defaultUserPhoto.png")
                .build());

        userRepository.save(User.builder()
                .firstName("Adam")
                .lastName("Macura")
                .username("Siterizer")
                .email("siterizer@gmail.com")
                .password("$2a$10$3g4oIfNqX51bvq7pICs1ReHex8tfb3Dp3eJ9U.MvrX.aPXF7folb6")//123
                .phoneNumber("696969696")
                .role(Role.USER)
                .photo("defaultUserPhoto.png")
                .build());

        userRepository.save(User.builder()
                .firstName("admin")
                .lastName("admin")
                .username("admin")
                .email("admin@gmail.com")
                .password("$2a$10$UPRK/oWCc3e.yTb5TLvGG.CdJP8aGP6jNJH.LCtvQhgvigse83VZG")//admin
                .phoneNumber("111111111")
                .role(Role.ADMIN)
                .photo("piotr-to-plaga.png")
                //.photo("RkiY54TDfygUrazdwaczy.png")
                .build());
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addAdvertisementsToDB() {
        log.info("Persisted advertisements data to database");

        advertisementRepository.save(Advertisement.builder()
                .advertisementCategory(AdvertisementCategory.ANIMALSCARE)
                .dateTime(LocalDateTime.now())
                .description("LAAAAAAAAAAAAAAAAAAA")
                .featured(false)
                .photos(null)
                .city(City.WARSZAWA)
                .title("TiTitle 2tle 1")
                .contractType(ContractType.MANDATORY_CONTRACT)
                .workingHours(WorkingHours.FULL_TIME)
                .createdBy("Papa")
                .reward(40)
                .build());

        advertisementRepository.save(Advertisement.builder()
                .advertisementCategory(AdvertisementCategory.HOUSEWORK)
                .dateTime(LocalDateTime.now())
                .description("LAAAAAAAAAAAAAAAAAAALA" +
                        "AALAAAAAAAAAAAAAAAAAAALAAAAAAAAAAAAAAAAAAALAAAAAAAAAAAAAAAAAAALAAAAA" +
                        "AAAAAAAAAAAAAALAAAAAAAAAAAAAAAAAAALAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA4AAAALAAAAAAAAAAAAAAAAAAA")
                .featured(false)
                .photos(null)
                .city(City.KRAKOW)
                .contractType(ContractType.EMPLOYMENT_CONTRACT)
                .title("TTitle 2Title 2Title 2itle 2")
                .workingHours(WorkingHours.HALF_TIME)
                .reward(100)
                .createdBy("Nana")
                .build());
    }
}
