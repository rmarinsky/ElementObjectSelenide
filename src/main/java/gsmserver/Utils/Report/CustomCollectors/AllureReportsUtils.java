package gsmserver.Utils.Report.CustomCollectors;

import com.google.common.io.Files;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;

import static com.codeborne.selenide.Screenshots.getLastScreenshot;


public class AllureReportsUtils {

    @Attachment(value = "Screenshot", type = "image/png")
    static byte[] attachScreenshot() throws IOException {
        return Files.toByteArray(getLastScreenshot());
    }

    @Attachment(value = "Test log")
    public static String attachText(String text) {
        return text;
    }

}
