
package gsmserver.SomeTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class SomeTest {

    @BeforeClass
    public static void olo() {
        Configuration.baseUrl = "http://gsmserver.com.gsm-s20.marinskyi.dev/";
        ChromeDriverManager.getInstance().setup();
        WebDriverRunner.setWebDriver(new ChromeDriver());
    }

    @Ignore
    @Test
    public void test() {
        open("");
        sleep(1000);
    }
}
