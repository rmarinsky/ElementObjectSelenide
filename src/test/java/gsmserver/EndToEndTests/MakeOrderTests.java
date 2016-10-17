package gsmserver.EndToEndTests;

import com.codeborne.selenide.SelenideElement;
import gsmserver.Components.Checkout.ContactInformation;
import gsmserver.Components.Checkout.DeliveryService;
import gsmserver.Components.Checkout.PaymentService;
import gsmserver.Components.Product;
import gsmserver.Components.User;
import gsmserver.Utils.BaseTest;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.Selenide.$;
import static gsmserver.Utils.Random.generateRandomEmail;
import static gsmserver.Utils.Random.generateRandomString;

@Description(value = "Tests for making orders")
public class MakeOrderTests extends BaseTest{

    @Before public void beforeMakeOrder(){
        new Product().addProductToCartViaJs(834632, 1);
        ContactInformation.openCheckoutPage();
    }

    @Title("Make simple order")
    @Test public void makeOrderOnComSite(){
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
