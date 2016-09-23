package gsmserver.Utils.Report.CustomCollectors;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;


public class AllureReportsUtils {

    @Attachment(value = "Screenshot", type = "image/png")
    static byte[] attachScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Test log")
    public static String attachText(String text) {
        return text;
    }

}
