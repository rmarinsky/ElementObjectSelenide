package gsmserver.Components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Utils.CustomConditions;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static gsmserver.Utils.CustomConditions.*;

public class LoginPopUp {

    private User user;

    LoginPopUp(){
        $("#login-view").shouldBe(Condition.visible);
        this.user = new User();
    }

    @Step
    public TopLinks loginUser(final String login, final String password){
        user.fillLogin(login).
                fillPassword(password);
        this.submit();
        return new TopLinks();
    }

    @Step
    private void submit(){
        $("button[type='submit']").click();
    }

    @Step
    private void shouldNotHaveClassError(SelenideElement element){
        element.shouldNotHave(classError());
    }

    @Step
    private void shouldHaveClassError(SelenideElement element){
        element.shouldHave(classError());
    }

    public LoginPopUp verifyLoginFormValidation(String value){
        this.user.login.shouldBe(Condition.empty);
        this.user.password.shouldBe(Condition.empty);
        CustomConditions.signaturesOfFieldsHaveRequiredLabel(user.login, user.password);
        this.user.login.click();
        this.user.password.click();
        $("td.buy-without-signup-message").click();
        this.shouldHaveClassError(this.user.login);
        this.shouldHaveClassError(this.user.password);
        this.submit();
        this.shouldHaveClassError($("div.form-summary"));
        this.user.fillLogin(value);
        $("td.buy-without-signup-message").click();
        this.shouldNotHaveClassError(this.user.login);
        this.user.fillPassword(value);
        $("td.buy-without-signup-message").click();
        this.shouldNotHaveClassError(this.user.password);
        this.user.fillLogin(value);
        this.user.password.clear();
        this.submit();
        shouldNotHaveClassError(this.user.login);
        this.user.password.shouldHave(classError(), cannotBeBlankTitleTip());
        this.shouldHaveClassError($("div.form-summary"));
        this.user.fillPassword(value);
        this.submit();
        this.user.login.shouldHave(classError(), originalTitleTip("Incorrect username or password"));
        this.user.password.shouldHave(classError(), originalTitleTip("Incorrect username or password"));
        this.shouldHaveClassError($("div.form-summary"));
        return this;
    }

}
