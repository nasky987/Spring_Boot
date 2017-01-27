package readinglist;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void addBookToEmptyList() {
        String baseUrl = "https://localhost:" + port;

        safariBrowser.get(baseUrl); //메인페이지 조회

        safariBrowser.findElementByName("username").sendKeys("hreeman");
        safariBrowser.findElementByName("password").sendKeys("1234");
        safariBrowser.findElementByTagName("form").submit();

        assertEquals("You have no books in your book list", safariBrowser.findElementByCssSelector("div > p").getText()); //빈 책 여부 검증(해당 문구 있는지 검사)

        safariBrowser.findElementByName("title").sendKeys("BOOK TITLE");
        safariBrowser.findElementByName("author").sendKeys("BOOK AUTHOR");
        safariBrowser.findElementByName("isbn").sendKeys("1234567890");
        safariBrowser.findElementByName("description").sendKeys("DESCRIPTION");
        safariBrowser.findElementByName("f").submit(); //폼에 데이터를 추가하고 전송

        WebElement dl = safariBrowser.findElementByCssSelector("dt.bookHeadline");
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)", dl.getText());

        WebElement dt = safariBrowser.findElementByCssSelector("dd.bookDescription");
        assertEquals("DESCRIPTION", dt.getText()); //목록에 새 책이 추가되었는지 검증
    }
}
