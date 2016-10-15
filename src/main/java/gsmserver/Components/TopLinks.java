package gsmserver.Components;

import com.codeborne.selenide.Condition;
import gsmserver.Components.Popups.AuthorisationPopup;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class TopLinks {

    public TopLinks(){
        $("#top-links").shouldBe(Condition.visible);
    }

    @Step
    public AuthorisationPopup openLoginPopup(){
        $("a.login").shouldBe(Condition.visible).click();
        return new AuthorisationPopup();
    }

    @Step
    public TopLinks logoutUser(){
        $("a.account").click();
        $("ul.submenu.shadow.account-dropdown").$(byLinkText("Log out")).click();
        $("a.login").shouldBe(Condition.visible);
        return this;
    }

    @Step
    public void userHaveName(final String name){
        $("li.account-name a").shouldHave(Condition.text(name));
    }

}
