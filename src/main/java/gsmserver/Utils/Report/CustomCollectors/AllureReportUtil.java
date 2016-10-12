package gsmserver.Utils.Report.CustomCollectors;

import com.automation.remarks.video.recorder.VideoRecorder;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;


public class AllureReportUtil {

    @Attachment(value = "Screenshot", type = "image/png")
    static byte[] attachScreenshot() {
        try {
            return com.google.common.io.Files.toByteArray(takeScreenShotAsFile());
        } catch (IOException e) {
            return new byte[0];
        }
    }

    @Attachment(value = "video", type = "video/mp4")
    public static byte[] attachVideo() {
        //File video = VideoRecorder.getLastRecording();
        System.out.println("PATH "+VideoRecorder.getLastRecording());
        try {
            if (VideoRecorder.conf().isVideoEnabled()) {
                System.out.println("VIDEO IS ENABLED");
            }
            File video = VideoRecorder.getLastRecording();
            return Files.readAllBytes(Paths.get(video.getAbsolutePath()));
            //return com.google.common.io.Files.toByteArray(VideoRecorder.getLastRecording());
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Attachment(value = "Log in table")
    public static String attachLog(String text) {
        return text;
    }

}
