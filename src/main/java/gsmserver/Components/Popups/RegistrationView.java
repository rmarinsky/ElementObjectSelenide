package gsmserver.Components.Popups;

import com.codeborne.selenide.Condition;
import gsmserver.Components.User;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static gsmserver.Utils.CustomConditions.submitForm;

public class RegistrationView {

    private User user;

    RegistrationView(){
        $("#registration-view").shouldBe(Condition.visible);
        user = new User();
    }

    @Step
    public void registerUser(final String firstNameValue, final String loginValue, final String emailValue){
        this.user.fillFirstName(firstNameValue).
                fillLogin(loginValue).
                fillEmail(emailValue);
        submitForm();
        $("#SignUpConfirm").shouldBe(Condition.visible);
    }

}
