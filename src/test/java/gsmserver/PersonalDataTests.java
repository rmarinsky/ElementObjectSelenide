package gsmserver;

import com.codeborne.selenide.Condition;
import gsmserver.Components.Account.AccountPersonalData;
import gsmserver.Components.GeneralForm;
import gsmserver.Components.User;
import gsmserver.Utils.BaseTest;
import gsmserver.Utils.Random;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static gsmserver.Utils.DefaultData.defaultEmail;
import static gsmserver.Utils.DefaultData.defaultPassword;

public class PersonalDataTests extends BaseTest{

    @Before
    public void beforeC() {
        new User().loginUserViaJS(defaultEmail, defaultPassword);
        AccountPersonalData.openPersonalDataPage();
    }

    @Test
    public void verifySavingPersonalDataTest() {
        String tempTestVal = defaultTestValue + Random.generateRandomString();
        User user = new User();
        user.login.is(Condition.readonly);
        user.fillFirstName(tempTestVal);
        user.fillLastName(tempTestVal);
        user.birthday.is(Condition.readonly);
        user.fillCity(tempTestVal);
        GeneralForm.submitForm().submitSucceeded();
        refresh();
        user.firstName.shouldHave(Condition.value(tempTestVal));
        user.lastName.shouldHave(Condition.value(tempTestVal));
        user.inputCity.shouldHave(Condition.value(tempTestVal));
    }

    @Test
    public void verifyVisibleOfRequiredLabelComTest(){
        AccountPersonalData.openPersonalDataPage().
                verifyVisibleOfRequiredLabelCom();
    }

    @Test
    public void verifySelectingCountryAndStateTest(){
        User user = new User();
        user.chooseCountry("Turkey");
        user.customCountry.$("em").shouldHave(Condition.text("Turkey"));
        GeneralForm.submitForm().submitSucceeded();
        user.customRegion.shouldNotBe(Condition.exist);
        user.chooseCountry("Spain").chooseRegion("Aragon");
        user.customRegion.$("em").shouldHave(Condition.text("Aragon"));
        GeneralForm.submitForm().submitSucceeded();
        refresh();
        user.customCountry.$("em").shouldHave(Condition.text("Spain"));
        user.customRegion.$("em").shouldHave(Condition.text("Aragon"));
    }

}
