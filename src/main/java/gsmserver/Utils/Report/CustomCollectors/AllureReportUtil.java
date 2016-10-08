package gsmserver.Utils.Report.CustomCollectors;

import com.google.common.io.Files;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;

import static com.codeborne.selenide.Screenshots.getLastScreenshot;


public class AllureReportUtil {

    @Attachment(value = "Screenshot", type = "image/png")
    static byte[] attachScreenshot() {
        try {
            return Files.toByteArray(getLastScreenshot());
        } catch (IOException e) {
            return new byte[0];
        }
    }

    @Attachment(value = "Test log")
    public static String attachLog(String text) {
        return text;
    }

}
