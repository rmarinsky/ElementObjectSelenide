package gsmserver.Components;

import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static gsmserver.Utils.JSExecutor.executeJS;

public class User {

    private final SelenideElement firstName = $("[name*='firstName']");
    private final SelenideElement lastName = $("[name*='lastName']");
    private final SelenideElement middleName = $("[name*='middleName']");
    private final SelenideElement email = $("[name*='email']");
    private final SelenideElement customCountry = $("#country_id_chosen");
    private final SelenideElement customRegion = $("#state_id_chosen");
    private final SelenideElement customCity = $("#city_id_chosen");
    private final SelenideElement inputCity = $("#city");
    private final SelenideElement address = $("[name*='[address]']"); //because of element have name = "address[address]"
    private final SelenideElement phoneCountries = $("a.styled-phone-dropdown-button");
    private final SelenideElement phoneNumber = $("div.styled-phone-edit > input");
    private final SelenideElement zip = $("[name*='zip'");
    private final SelenideElement taxId = $("[name*='taxId']");

    @Step public User fillFirstName(final String value){
        this.firstName.setValue(value);
        return this;
    }

    @Step public User fillLastName(final String value) {
        this.lastName.setValue(value);
        return this;
    }

    @Step public User fillMiddleName(final String value) {
        this.middleName.setValue(value);
        return this;
    }

    @Step public User fillEmail(final String value) {
        this.email.setValue(value);
        return this;
    }

    @Step public User fillAddress(final String value) {
        this.address.setValue(value);
        return this;
    }


    @Step public User fillPhoneByCountry(final String country, final String phone){
        this.phoneCountries.click();
        $(String.format("b[data-hint='%s']", country)).scrollTo().click();
        this.phoneNumber.setValue(phone);
        return this;
    }

    @Step public User chooseCountry(final String value){
        $("h1").scrollTo();
        this.customCountry.click();
        this.selectItem(value);
        return this;
    }

    @Step public User chooseRegion(final String value){
        $("h1").scrollTo();
        this.customRegion.click();
        this.selectItem(value);
        return this;
    }

    @Step public User chooseCity(final String value){
        $("h1").scrollTo();
        this.customCity.click();
        this.selectItem(value);
        return this;
    }

    @Step public User fillCity(final String value){
        this.inputCity.setValue(value);
        return this;
    }

    private void selectItem(String nameOfItem){
        $(byXpath(String.format("//li//span[.='%s']", nameOfItem))).scrollTo().click();
    }

    @Step public void loginUserViaJS(final String login, final String password){
        executeJS().postRequestWithParams("/user/login/", String.format("{'login[username]':'%s', 'login[password]':'%s'}", login, password));
    }

    @Step public void setCountryUSAForUserViaJS(){
        executeJS().postRequestWithParams("/account/data/", "{'account[countryId]':'840', 'account[stateId]':'-1'}");
    }

    @Step public void setCountrySpainForUserViaJS(){
        executeJS().postRequestWithParams("/account/data/", "{'account[countryId]':'724', 'account[stateId]':'-1'}");
    }

}
