package odd.jobs.configuration;

import lombok.extern.slf4j.Slf4j;
import odd.jobs.entities.advertisement.Advertisement;
import odd.jobs.entities.advertisement.AdvertisementCategory;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.AdvertisementRepository;
import odd.jobs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class InitialData {
    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public InitialData(UserRepository userRepository, AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
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
                .build());

        userRepository.save(User.builder()
                .firstName("Adam")
                .lastName("Macura")
                .username("Siterizer")
                .email("siterizer@gmail.com")
                .password("$2a$10$3g4oIfNqX51bvq7pICs1ReHex8tfb3Dp3eJ9U.MvrX.aPXF7folb6")//123
                .phoneNumber("696969696")
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
                .title("TiTitle 2tle 1")
                .build());

        advertisementRepository.save(Advertisement.builder()
                .advertisementCategory(AdvertisementCategory.ANIMALSCARE)
                .dateTime(LocalDateTime.now())
                .description("LAAAAAAAAAAAAAAAAAAALA" +
                        "AALAAAAAAAAAAAAAAAAAAALAAAAAAAAAAAAAAAAAAALAAAAAAAAAAAAAAAAAAALAAAAA" +
                        "AAAAAAAAAAAAAALAAAAAAAAAAAAAAAAAAALAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA4AAAALAAAAAAAAAAAAAAAAAAA")
                .featured(false)
                .photos(null)
                .title("TTitle 2Title 2Title 2itle 2")
                .build());
    }
}
