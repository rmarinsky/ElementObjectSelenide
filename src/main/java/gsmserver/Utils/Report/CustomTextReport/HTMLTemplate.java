package gsmserver.Utils.Report.CustomTextReport;

import com.codeborne.selenide.logevents.EventsCollector;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.SelenideLogger;
import gsmserver.Utils.Report.CustomCollectors.AllureReportUtil;
import org.rendersnake.HtmlCanvas;
import java.io.IOException;
import java.util.logging.Logger;
import static org.rendersnake.HtmlAttributesFactory.border;

class HTMLTemplate {

    private static final Logger log = Logger.getLogger(HTMLTemplate.class.getName());

    void start() {
        SelenideLogger.addListener("reporter", new EventsCollector());
    }

    void finish() {
        EventsCollector logEventListener = SelenideLogger.removeListener("reporter");
        HtmlCanvas html = new HtmlCanvas();
        try {
            html.html().table(border("1")).tr().
                    th().content("Element").th().content("Subject").th().content("Status").th().content("ms")
                    ._tr();
            for (LogEvent e : logEventListener.events()){
                html.tr()
                        .td().content(e.getElement())
                        .td().content(e.getSubject())
                        .td().content(String.valueOf(e.getStatus()))
                        .td().content(String.valueOf(e.getDuration()))
                        ._tr();
            }
            html._table()._html();
            log.info(html.toHtml());
            AllureReportUtil.attachLog(html.toHtml());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void clean() {
        SelenideLogger.removeListener("reporter");
    }
}
