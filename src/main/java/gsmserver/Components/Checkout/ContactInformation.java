package gsmserver.Components.Checkout;

import gsmserver.Components.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static gsmserver.Utils.CustomConditions.signaturesOfFieldsHaveRequiredLabel;

public class ContactInformation {

    private User user;

    public ContactInformation(){
        $(".checkout-delivery-new-address").shouldBe(visible);
        this.user = new User();
    }

    public static ContactInformation openCheckoutPage(){
        open("/checkout/");
        return new ContactInformation();
    }

    public ContactInformation verifyCountriesWithRequiredTaxId(String... countries) {
        for(String country : countries) {
            this.user.chooseCountry(country);
            signaturesOfFieldsHaveRequiredLabel(this.user.taxId);
        }
        return this;
    }

    public ContactInformation verifyCountriesWithRequiredMiddleName(String... countries){
        for(String country : countries) {
            this.user.chooseCountry(country);
            signaturesOfFieldsHaveRequiredLabel(this.user.middleName);
        }
        return this;
    }

    public ContactInformation verifyCountriesWithRequiredRegion(String... countries){
        for(String country : countries) {
            this.user.chooseCountry(country);
            signaturesOfFieldsHaveRequiredLabel(this.user.customRegion);
            this.user.customRegion.click();
            $("#state_id > option").shouldBe(visible);
        }
        return this;
    }

}
