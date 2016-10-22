package gsmserver.Utils;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Configuration.baseUrl;

public class JSExecutor {

    public JSExecutor(){
        Selenide.executeJavaScript("$(document).ready()");
    }

    public void POSTWithParams(final String requestUrl, final String param){
        Selenide.executeJavaScript(String.format("$.post('%s%s', %s)", baseUrl, requestUrl, param));
    }

    public void GETWithParam(final String requestUrl, final String param){
        Selenide.executeJavaScript(String.format("$.get('%s', %s)", requestUrl, param));
    }

    /**
     * Make GET request to baseUrl
     * @param request
     * request with params
     */
    public void GETRequest(final String request){
        Selenide.executeJavaScript(String.format("$.get('%s%s')", baseUrl, request));
    }

}
