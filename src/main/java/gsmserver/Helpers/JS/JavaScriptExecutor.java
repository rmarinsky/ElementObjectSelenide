package gsmserver.Helpers.JS;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.sleep;

class JavaScriptExecutor {

    JavaScriptExecutor sendPOSTRequestWithParams(final String requestUrl, final String param){
        Selenide.executeJavaScript(String.format("$.post('%s%s', %s)", baseUrl, requestUrl, param));
        return this;
    }

    JavaScriptExecutor sendGETRequestWithParams(final String requestUrl, final String param){
        Selenide.executeJavaScript(String.format("$.get('%s', %s)", requestUrl, param));
        return this;
    }

    JavaScriptExecutor sendSimpleGETRequest(final String request){
        Selenide.executeJavaScript(String.format("$.get('%s%s')", baseUrl, request));
        sleep(200);
        return this;
    }

}
