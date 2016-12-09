package gsmserver.Components.Checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.WebDriverRunner;
import gsmserver.Components.User;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.*;
import static gsmserver.Utils.CustomConditions.signaturesOfFieldsHaveRequiredLabel;

public class Checkout {

    private User user;

    public Checkout(){
        if($("[data-sm-role='checkout.container']").is(Condition.hidden) && WebDriverRunner.getWebDriver().getCurrentUrl().contains("/cart/"))
            open("/checkout/");
        $("[data-sm-role='checkout.container']").shouldBe(Condition.visible);
        user = new User();
    }

    @Step
    public static Checkout openCheckoutPage(){
        open("/checkout/");
        return new Checkout();
    }

    @Step("Check that countries: [{0}], have required filed tax id")
    public Checkout verifyCountriesWithRequiredTaxId(String... countries) {
        for(String country : countries) {
            user.chooseCountry(country);
            signaturesOfFieldsHaveRequiredLabel(user.fieldTaxId);
        }
        return this;
    }

    @Step("Check that countries: [{0}], have required filed middle name")
    public Checkout verifyCountriesWithRequiredMiddleName(String... countries){
        for(String country : countries) {
            user.chooseCountry(country);
            signaturesOfFieldsHaveRequiredLabel(user.fieldMiddleName);
        }
        return this;
    }

    @Step("Check that countries: [{0}], have required filed Region")
    public Checkout verifyCountriesWithRequiredRegion(String... countries){
        for(String country : countries) {
            user.chooseCountry(country);
            signaturesOfFieldsHaveRequiredLabel(user.customRegion);
            $(user.customRegion).click();
            $(user.customRegion).$(".chosen-drop").shouldBe(Condition.visible);
        }
        return this;
    }

    @Step("Products: [{0}], forbidden to sell")
    public Checkout shouldBeVisibleNotificationForbiddenToSellProduct(Integer... idProduct){
        $("#products-forbidden-to-sell-in-user-country").shouldBe(Condition.visible, Condition.and ("Have notification", Condition.text("Not for the USA")));
        for(Integer product : idProduct){
            $$(".product-id").findBy(Condition.text(String.valueOf(product)));
        }
        return this;
    }

    @Step("Click on button 'Remove' and check that cart have no product:: [{0}]")
    public Checkout removeAndCheckRemovingInCart(Integer idProduct){
        $(".button-okay").click();
        $("#cart").shouldBe(Condition.exist);
        $(Selectors.withText(String.valueOf(idProduct))).shouldBe(Condition.hidden);
        return this;
    }

    @Step
    public User openEditAddressForm(){
        if($("[data-sm-role='checkout.edit']").is(Condition.visible) && $(user.fieldFirstName).is(Condition.hidden))
            $("[data-sm-role='checkout.edit']").click();
        return new User(true);
    }

}
