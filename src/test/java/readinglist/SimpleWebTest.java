package readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by hreeman on 1/21/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SimpleWebTest {
    @Test(expected = HttpClientErrorException.class)
    public void pageNotFound() {
     try {
         RestTemplate restTemplate = new RestTemplate();
         restTemplate.getForObject("http://localhost:8080/bogusPage", String.class);
         fail("Should result in HTTP 404");
     } catch (HttpClientErrorException exception) {
         assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
         throw exception;
     }
    }
}
