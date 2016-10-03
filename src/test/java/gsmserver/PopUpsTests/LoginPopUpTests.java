package gsmserver.PopUpsTests;

import gsmserver.Components.TopLinks;
import gsmserver.Utils.BaseTest;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;

import static gsmserver.Utils.Random.generateRandomString;

public class LoginPopUpTests extends BaseTest {

    @Test public void loginUserTest(){
        new TopLinks().openLoginPopup().
                loginUser("email@test.com", "1111").
                userHaveName("email@test.com");
    }

    @Description("This is a complex verification for login form, with scenarios from empty form and values to fullfill form inputs")
    @Test public void verifyFormValidationTest(){
        new TopLinks().openLoginPopup().
                verifyLoginFormValidation(generateRandomString());
    }

}
