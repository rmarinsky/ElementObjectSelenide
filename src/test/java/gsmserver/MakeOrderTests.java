package gsmserver;

import com.codeborne.selenide.SelenideElement;
import gsmserver.Components.DeliveryService;
import gsmserver.Components.PaymentService;
import gsmserver.Components.User;
import gsmserver.Helpers.JS.JSProductHelper;
import gsmserver.Utils.BaseTest;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static gsmserver.Utils.Random.generateRandomEmail;
import static gsmserver.Utils.Random.generateRandomString;

@Description(value = "Tests for making orders")
public class MakeOrderTests extends BaseTest{

    @Before public void before(){
        open("");
        new JSProductHelper().addProductToCart(834632, 1);
        open("/checkout/");
    }

    @Title("Make simple order")
    @Test public void makeOrder(){
        String country = "Sweden";
        String skrillPaymentService = "2032449552";
        new User().fillFirstName(generateRandomString("firstName")).
                fillLastName(generateRandomString("lastName")).
                fillEmail(generateRandomEmail()).
                fillPhoneByCountry(country, "111222333").
                chooseCountry(country).fillCity(generateRandomString("city")).
                fillAddress(generateRandomString("address"));
        SelenideElement nextStep = $(".checkout-button-next-wrapper > a");
        nextStep.click();
        new DeliveryService().chooseDeliveryServiceAndVerifyCost("DHL");
        nextStep.click();
        new PaymentService().choosePaymentServiceAndCheckCost(skrillPaymentService);
        nextStep.click();
        $("textarea").setValue("test");
    }


}
