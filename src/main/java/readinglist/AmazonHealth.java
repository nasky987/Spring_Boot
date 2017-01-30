package readinglist;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hreeman on 1/30/17.
 */

@Component
public class AmazonHealth implements HealthIndicator {

    @Override
    public Health health() {
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject("https://www.amazon.com", String.class);

            return health().up().build();
        } catch (Exception e) {
            return health().down().build();
        }
    }
}
