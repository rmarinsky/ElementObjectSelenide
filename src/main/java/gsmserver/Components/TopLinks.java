package gsmserver.Components;

import com.codeborne.selenide.Condition;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;

public class TopLinks {

    public TopLinks(){
        $("#top-links").shouldBe(Condition.visible);
    }

    @Step
    public LoginPopUp openLoginPopup(){
        $("a.login").shouldBe(Condition.visible).click();
        return new LoginPopUp();
    }

    @Step
    public void userHaveName(final String name){
        $("li.account-name a").shouldHave(Condition.text(name));
    }

}
