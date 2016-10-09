package com.tempmail;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Pages.ConfirmationPage;
import ru.yandex.qatools.allure.annotations.Step;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

public class TempMail {

    private TempMail(){
        $(".logo").shouldHave(Condition.text("Temp Mail"));
    }

    @Step
    public static TempMail openTempMail(){
        open("https://temp-mail.ru/");
        return new TempMail();
    }

    private static String tempEmail;

    public static String getTempEMail() {
        return tempEmail;
    }

    private static String tempPassword;
    public static String getTempPassword() {
        return tempPassword;
    }
    public static void setTempPassword(String tempPassword) {
        if(tempPassword != null)
            TempMail.tempPassword = tempPassword;
    }


    public TempMail saveEmailAddress(){
        TempMail.tempEmail = $("#mail").getValue();
        System.out.println(tempEmail);
        return this;
    }

    private SelenideElement waitForLinkOnPage(String linkText){
        int i = 0;
            while (!$(byLinkText(linkText)).exists()) {
                i++;
                sleep(100);
                Selenide.refresh();
                sleep(100);
                if(i == 50){
                    break;
                }
            }
        return $(byLinkText(linkText));
    }

    @Step
    private void goByConfirmationLink(){
        try {
            URL redirectUrl = new URL(waitForLinkOnPage("this link").getAttribute("href"));
            open(redirectUrl.getFile());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Step
    public ConfirmationPage confirmRegistration() {
        this.waitForLinkOnPage("Registration at GsmServer").click();
        goByConfirmationLink();
        return new ConfirmationPage();
    }

    @Step
    public ConfirmationPage conformPasswordRecovery(){
        this.waitForLinkOnPage("Password Recovery Confirm").click();
        goByConfirmationLink();
        return new ConfirmationPage();
    }

}
