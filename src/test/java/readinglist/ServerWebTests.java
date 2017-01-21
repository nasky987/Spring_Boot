package readinglist;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by hreeman on 1/21/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerWebTests {
    private static SafariDriver safariBrowser; //책에서는 FireFox브라우저를 이용하여 FireFoxDriver를 사용했다.

    @Value("${local.server.port}")
    private int port; //임의의 포트값 주입

    @BeforeClass
    public static void openBrowser() {
        safariBrowser = new SafariDriver();
        safariBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //사파리 브라우저 드라이버 설정
    }

    @AfterClass
    public static void closeBrowser() {
        safariBrowser.quit(); //브라우저 종료
    }
}
