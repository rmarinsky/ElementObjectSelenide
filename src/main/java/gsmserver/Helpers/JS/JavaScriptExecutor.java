package gsmserver.Helpers.JS;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.sleep;

class JavaScriptExecutor {

    /**
     * !!!Важно!!! перед выполнением JavaScript страница должна быть загружена. Т.к. может быть проблема с не загруженным JS в браузере
     * Выполнить jQuery метод, который отправит POST запрос в браузере в рамках сессии текущего теста
     * @param requestUrl
     * Ссылка на которую отправить POST запрос
     * @param param
     * Параметр/-ы, которые отправить вместе с url в запросе
     * @return
     * Возвращает объект JavaScriptExecution (самого себя)
     */
    JavaScriptExecutor sendPOSTRequestWithParams(final String requestUrl, final String param){
        Selenide.executeJavaScript(String.format("$.post('%s%s', %s)", baseUrl, requestUrl, param));
        return this;
    }

    /**
     * !!!Важно!!! перед выполнением JavaScript страница должна быть загружена. Т.к. может быть проблема с не загруженным JS в браузере
     * Выполнить jQuery метод, который отправит GET запрос в браузере в рамках сессии текущего теста
     * @param requestUrl
     * Ссылка на которую отправить GET запрос
     * @param param
     * Параметр/-ы, которые отправить вместе с url в запросе
     * @return
     * Возвращает объект JavaScriptExecution (самого себя)
     */
    JavaScriptExecutor sendGETRequestWithParams(final String requestUrl, final String param){
        Selenide.executeJavaScript(String.format("$.get('%s', %s)", requestUrl, param));
        return this;
    }

    /**
     * !!!Важно!!! перед выполнением JavaScript страница должна быть загружена. Т.к. может быть проблема с не загруженным JS в браузере
     * Выполнить jQuery метод отправит простой GET запрос в браузере в рамках сессии текущего теста, на URL, БЕЗ ПАРАМЕТРОВ
     * @param request
     * Ссылка на которую отправить GET запрос
     * @return
     * Возвращает объект JavaScriptExecutor (самого себя)
     */
    JavaScriptExecutor sendSimpleGETRequest(final String request){
        Selenide.executeJavaScript(String.format("$.get('%s%s')", baseUrl, request));
        sleep(200);
        return this;
    }

}
