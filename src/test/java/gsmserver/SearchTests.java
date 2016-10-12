package gsmserver;


import com.automation.remarks.video.exception.RecordingException;
import com.automation.remarks.video.recorder.ffmpeg.FFmpegWrapper;
import gsmserver.Components.Search;
import gsmserver.Utils.BaseTest;
import org.junit.Test;
import org.zeroturnaround.exec.ProcessExecutor;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

import static com.automation.remarks.video.SystemUtils.getScreenSize;
import static com.automation.remarks.video.recorder.VideoRecorder.conf;


@Description(value = "Tests for searching on site")
public class SearchTests extends BaseTest {

    @Title(value = "Test for search")
    @Test public void searchTest(){
        new Search().searchFor("smart-clip").
                searchResultShouldHasSearchedPhrase();
    }

    public static void startFFmpeg(String display, String recorder, String... args) {
        File videoFolder = conf().getVideoFolder();
        if (!videoFolder.exists()) {
            videoFolder.mkdirs();
        }

        final String[] commandsSequence = new String[]{
                FFmpegWrapper.RECORDING_TOOL, "-y",
                "-video_size", getScreenSize(),
                "-f", recorder,
                "-i", display,
                "-an",
                "-framerate", String.valueOf(conf().getFrameRate()),
                "temporaryFile.getAbsolutePath()"
        };
        CompletableFuture<String> future;
        List<String> command = new ArrayList<>();
        command.addAll(Arrays.asList(commandsSequence));
        command.addAll(Arrays.asList(args));
        CompletableFuture.supplyAsync(() -> runCommand(command));
    }

    public static String runCommand(final List<String> args) {
        System.out.println("Trying to execute the following command: " + args);
        try {
            return new ProcessExecutor()
                    .command(args)
                    .readOutput(true)
                    .execute()
                    .outputUTF8();
        } catch (IOException | InterruptedException | TimeoutException e) {
            System.out.println("Unable to execute command: " + e);
            throw new RecordingException(e);
        }
    }

    public static void main(String[] args) {
        startFFmpeg("desktop", "gdigrab");
        //System.out.println("cmd /c for /f \"tokens=2\" %i in ('tasklist ^| findstr \"" + "SendSignalCtrlC.exe" + "\"') do @echo %i");
    }

}
