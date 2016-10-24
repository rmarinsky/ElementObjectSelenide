package gsmserver;

import com.codeborne.selenide.Condition;
import gsmserver.Components.Account.AccountPersonalData;
import gsmserver.Components.MainComponent;
import gsmserver.Components.User;
import gsmserver.Pages.HomePage;
import gsmserver.Utils.BaseTest;
import gsmserver.Utils.Random;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static gsmserver.Utils.DefaultData.defaultEmail;
import static gsmserver.Utils.DefaultData.defaultPassword;

public class PersonalDataTests extends BaseTest{

    @BeforeClass
    public static void beforeClass() {
        HomePage.openHomePage();
        new User().loginUserViaJS(defaultEmail, defaultPassword);
        AccountPersonalData.openPersonalDataPage();
    }

    @Test
    public void verifySavingPersonalDataTest() {
        String tempTestVal = Random.generateRandomString();
        User user = new User();
        $(user.fieldLogin).is(Condition.readonly);
        user.fillFirstName(tempTestVal);
        user.fillLastName(tempTestVal);
        $(user.customBirthday).is(Condition.readonly);
        user.fillCity(tempTestVal);
        MainComponent.submitForm().submitShouldBeSucceeded();
        refresh();
        $(user.fieldFirstName).shouldHave(Condition.value(tempTestVal));
        $(user.fieldLastName).shouldHave(Condition.value(tempTestVal));
        $(user.fieldCity).shouldHave(Condition.value(tempTestVal));
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
        user.shouldHaveText(user.customCountry,"Turkey");
        MainComponent.submitForm().submitShouldBeSucceeded();
        $(user.customRegion).shouldNotBe(Condition.exist);
        user.chooseCountry("Spain").chooseRegion("Aragon");
        user.shouldHaveText(user.customRegion,"Aragon");
        MainComponent.submitForm().submitShouldBeSucceeded();
        refresh();
        user.shouldHaveText(user.customCountry,"Spain");
        user.shouldHaveText(user.customRegion, "Aragon");
    }

}
