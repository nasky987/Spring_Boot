package readinglist;

import org.springframework.boot.actuate.trace.InMemoryTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hreeman on 1/30/17.
 */

@Configuration
public class ActuatorConfig {

    @Bean
    public InMemoryTraceRepository traceRepository() {
        InMemoryTraceRepository traceRepository = new InMemoryTraceRepository();

        traceRepository.setCapacity(1000);

        return traceRepository;
    }
}
