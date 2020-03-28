package odd.jobs.configuration;

import lombok.extern.slf4j.Slf4j;
import odd.jobs.entities.user.User;
import odd.jobs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitialData {

    private final UserRepository userRepository;

    @Autowired
    public InitialData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addUsersToDB() {
        log.info("Persisted account data to database");

        userRepository.save(User.builder()
                .firstName("Macko")
                .lastName("Bogdaniec")
                .username("must1")
                .password("$2a$10$3g4oIfNqX51bvq7pICs1ReHex8tfb3Dp3eJ9U.MvrX.aPXF7folb6")//123
                .build());

        userRepository.save(User.builder()
                .firstName("Adam")
                .lastName("Macura")
                .username("Siterizer")
                .password("$2a$10$3g4oIfNqX51bvq7pICs1ReHex8tfb3Dp3eJ9U.MvrX.aPXF7folb6")//123
                .build());
    }
}
