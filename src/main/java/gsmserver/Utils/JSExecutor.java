package gsmserver.Utils;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.sleep;

public class JSExecutor {

    private JSExecutor(){}

    public static JSExecutor executeJS(){
        return new JSExecutor();
    }

    public void postRequestWithParams(final String requestUrl, final String param){
        sleep(200);
        Selenide.executeJavaScript(String.format("$.post('%s%s', %s)", baseUrl, requestUrl, param));
        sleep(200);
    }

    public void getRequestWithParams(final String requestUrl, final String param){
        Selenide.executeJavaScript(String.format("$.get('%s', %s)", requestUrl, param));
    }

    /**
     * Make GET request to baseUrl
     * @param request
     * request with params
     */
    public void simpleGETRequest(final String request){
        Selenide.executeJavaScript(String.format("$.get('%s%s')", baseUrl, request));
        sleep(200);
    }

}
