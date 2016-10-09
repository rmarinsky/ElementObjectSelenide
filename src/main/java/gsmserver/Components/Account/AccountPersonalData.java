package gsmserver.Components.Account;

import com.codeborne.selenide.Condition;
import gsmserver.Components.User;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AccountPersonalData {

    private User user;

    private AccountPersonalData(){
        $("#account-data-view").shouldBe(Condition.visible);
        user = new User();
    }

    @Step
    public static AccountPersonalData openPersonalDataPage(){
        open("/account/data/");
        return new AccountPersonalData();
    }

    @Step
    public void loginEmailFirstNameShouldBe(final String loginValue, final String emailValue, final String firstNameValue){
        this.user.login.has(Condition.value(loginValue));
        this.user.email.has(Condition.value(emailValue));
        this.user.firstName.has(Condition.value(firstNameValue));
    }

}
