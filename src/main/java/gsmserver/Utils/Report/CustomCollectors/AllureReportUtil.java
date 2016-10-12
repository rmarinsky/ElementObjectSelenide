package gsmserver.Utils.Report.CustomCollectors;

import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;


public class AllureReportUtil {

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        try {
            return com.google.common.io.Files.toByteArray(takeScreenShotAsFile());
        } catch (IOException e) {
            return new byte[0];
        }
    }

    @Attachment(value = "VIDEO", type = "video/mp4")
    public byte[] attachVideo(File file) {
        System.out.println("PATH "+ file.getAbsolutePath());
        try {
            return com.google.common.io.Files.toByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    /*@Attachment(value = "{0}", type = "video/mp4")
    public File attachVideo(File files) {
        System.out.println("PATH "+ files.getAbsolutePath());
        return files.getAbsoluteFile();
    }*/

    @Attachment(value = "Log in table")
    public static String attachLog(String text) {
        return text;
    }

}
