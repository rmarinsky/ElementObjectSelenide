package gsmserver.Utils.Report;

import com.automation.remarks.video.recorder.VideoRecorder;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;

import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;
import static com.google.common.io.Files.toByteArray;


class AllureReportUtil {

    @Attachment(value = "Screenshot", type = "image/png")
    static byte[] attachScreenshot() {
        try {
            return toByteArray(takeScreenShotAsFile());
        } catch (IOException e) {
            return new byte[0];
        }
    }

    @Attachment(value = "video record", type = "video/mp4")
    static byte[] attachVideo() {
        try {
            return toByteArray(VideoRecorder.getLastRecording());
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Attachment(value = "Log in table")
    static String attachLog(String text) {
        return text;
    }

}
