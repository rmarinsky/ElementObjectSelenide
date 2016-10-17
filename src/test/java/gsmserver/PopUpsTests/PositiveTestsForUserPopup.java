package gsmserver.PopUpsTests;

import gsmserver.Components.TopLinks;
import gsmserver.Pages.HomePage;
import gsmserver.Utils.BaseTest;
import org.junit.Before;
import org.junit.Test;

import static gsmserver.Utils.DefaultData.defaultEmail;

public class PositiveTestsForUserPopup extends BaseTest {

    @Before
    public void beforeTest(){
        HomePage.openHomePage();
    }

    @Test
    public void loginUserTest(){
        new TopLinks().openLoginPopup().
                loginUser("email@test.com", "1111");
    }

    @Test
    public void recoveryPasswordForUserTest(){
        new TopLinks().openLoginPopup().openForgotPasswordForm().
                recoveryPasswordForUser(defaultEmail);
    }

}
