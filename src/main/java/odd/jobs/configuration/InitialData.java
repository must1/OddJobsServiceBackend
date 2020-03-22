package odd.jobs.configuration;

import lombok.extern.slf4j.Slf4j;
import odd.jobs.entities.User;
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
                .name("Macko")
                .surname("Bogdaniec")
                .build());

        userRepository.save(User.builder()
                .name("Michal")
                .surname("Michalowicz")
                .build());
    }
}
