package gsmserver.Components.Checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import gsmserver.Components.User;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.*;
import static gsmserver.Utils.CustomConditions.signaturesOfFieldsHaveRequiredLabel;

public class ContactInformation {

    private User user;

    public ContactInformation(){
        $("[data-sm-role='checkout.container']").shouldBe(Condition.visible);
        user = new User();
    }

    @Step
    public static ContactInformation openCheckoutPage(){
        open("/checkout/");
        return new ContactInformation();
    }

    @Step
    public ContactInformation verifyCountriesWithRequiredTaxId(String... countries) {
        for(String country : countries) {
            user.chooseCountry(country);
            signaturesOfFieldsHaveRequiredLabel(user.fieldTaxId);
        }
        return this;
    }

    @Step
    public ContactInformation verifyCountriesWithRequiredMiddleName(String... countries){
        for(String country : countries) {
            user.chooseCountry(country);
            signaturesOfFieldsHaveRequiredLabel($(user.fieldMiddleName));
        }
        return this;
    }

    @Step
    public ContactInformation verifyCountriesWithRequiredRegion(String... countries){
        for(String country : countries) {
            user.chooseCountry(country);
            signaturesOfFieldsHaveRequiredLabel(user.customRegion);
            user.customRegion.click();
            user.customRegion.$(".chosen-drop").shouldBe(Condition.visible);
        }
        return this;
    }

    @Step
    public ContactInformation shouldBeVisibleNotificationForbiddenToSellProduct(Integer... idProduct){
        $("#products-forbidden-to-sell-in-user-country").shouldBe(Condition.visible, Condition.and ("Have notification", Condition.text("Not for the USA")));
        for(Integer product : idProduct){
            $$(".product-id").findBy(Condition.text(String.valueOf(product)));
        }
        return this;
    }

    @Step
    public ContactInformation removeAndCheckRemovingInCart(Integer idProduct){
        $(".button-okay").click();
        $("#cart").shouldBe(Condition.exist);
        $(Selectors.withText(String.valueOf(idProduct))).shouldBe(Condition.hidden);
        return this;
    }

    @Step
    public User openEditAddressForm(){
        if($("[data-sm-role='checkout.edit']").is(Condition.visible) && user.fieldFirstName.is(Condition.hidden))
            $("[data-sm-role='checkout.edit']").click();
        return new User(true);
    }

}
