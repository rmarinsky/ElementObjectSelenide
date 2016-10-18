package gsmserver.Components.Account;

import com.codeborne.selenide.Condition;
import gsmserver.Components.User;
import gsmserver.Utils.CustomConditions;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byCssSelector;
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

    @Step("Check that 'login' has value: [{0}], 'email' has value: [{1}], 'firstName' has value: [{2}]")
    public void loginEmailFirstNameShouldBe(final String loginValue, final String emailValue, final String firstNameValue){
        $(user.fieldLogin).has(Condition.value(loginValue));
        $(user.fieldEmail).has(Condition.value(emailValue));
        $(user.fieldFirstName).has(Condition.value(firstNameValue));
    }

    @Step("Check that elements are required: First name, Country, Email. Check that elements are NOT required: Last name, Birthday, Region, City, Phone")
    public void verifyVisibleOfRequiredLabelCom(){
        CustomConditions.signaturesOfFieldsHaveRequiredLabel(user.fieldFirstName, user.customCountry, user.fieldEmail);
        CustomConditions.signaturesOfFieldsHaveNoRequiredLabel(user.fieldLastName, user.customBirthday,
                user.customRegion, user.fieldCity, byCssSelector("div.styled-phone-input"));
    }

}
