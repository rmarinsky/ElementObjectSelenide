package gsmserver.Components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Components.Account.AccountPersonalData;
import gsmserver.Utils.JSExecutor;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class User {

    private boolean useComponent = false;

    private SelenideElement formAddress = $("form[name='address']");

    @SuppressWarnings("WeakerAccess")
    public final By fieldLogin = byCssSelector("input[name*='username']"),
            fieldPassword = byCssSelector("input[name*='password']"),
            fieldPasswordConfirm = byCssSelector("input[name*='password_confirm']"),
            fieldFirstName = byCssSelector("input[name*='firstName']"),
            fieldEmail = byCssSelector("input[name*='email']"),
            fieldLastName = byCssSelector("input[name*='lastName']"),
            fieldCity = byCssSelector("input#city"),
            fieldMiddleName = byCssSelector("input[name*='middleName']"),
            customCountry = byCssSelector("#country_id_chosen"),
            customRegion = byCssSelector("#state_id_chosen"),
            customCity = byCssSelector("#city_id_chosen"),
            fieldAddress = byCssSelector("input[name*='[address]']"), //because of element have name = "address[address]"
            customPhoneCountries = byCssSelector("a.styled-phone-dropdown-button"),
            fieldPhoneNumber = byCssSelector(".styled-phone-edit > input"),
            customBirthday = byCssSelector("#birthday"),
            fieldZip = byCssSelector("input[name*='zip'"),
            fieldTaxId = byCssSelector("input[name*='taxId']");

    public User(){
    }

    public User(boolean useComponent){
        this.useComponent = useComponent;
    }

    @Step
    public User fillLogin(final String value){
        $(this.fieldLogin).sendKeys(value);
        return this;
    }

    @Step
    public User fillPassword(final String value){
        $(this.fieldPassword).sendKeys(value);
        return this;
    }

    @Step
    public User fillConfirmPassword(final String value){
        $(this.fieldPasswordConfirm).sendKeys(value);
        return this;
    }

    @Step
    public User fillFirstName(final String value){
        if(useComponent){
            formAddress.$(this.fieldFirstName).setValue(value);
        } else {
            $(this.fieldFirstName).setValue(value);
        }
        return this;
    }

    @Step
    public User fillLastName(final String value) {
        if(useComponent){
            formAddress.$(this.fieldLastName).setValue(value);
        } else {
            $(this.fieldLastName).setValue(value);
        }
        return this;
    }

    @Step
    public User fillMiddleName(final String value) {
        if(useComponent){
            formAddress.$(this.fieldMiddleName).setValue(value);
        } else {
            $(this.fieldMiddleName).setValue(value);
        }
        return this;
    }

    @Step
    public User fillEmail(final String value) {
        if(useComponent){
            formAddress.$(this.fieldEmail).setValue(value);
        } else {
            $(this.fieldEmail).setValue(value);
        }
        return this;
    }

    @Step
    public User fillAddress(final String value) {
        if(useComponent){
            formAddress.$(this.fieldAddress).setValue(value);
        } else {
            $(this.fieldAddress).setValue(value);
        }
        return this;
    }


    @Step
    public User fillPhoneByCountry(final String country, final String phone){
        $(this.customPhoneCountries).click();
        $(String.format("b[data-hint='%s']", country)).scrollTo().click();
        $(this.fieldPhoneNumber).setValue(phone);
        return this;
    }

    @Step
    public User chooseCountry(final String value){
        chooseCustom(this.customCountry, value);
        return this;
    }

    @Step
    public User chooseRegion(final String value){
        this.chooseCustom(this.customRegion, value);
        return this;
    }

    @Step
    public User chooseCity(final String value){
        this.chooseCustom(this.customCity, value);
        return this;
    }

    private void chooseCustom(final By customElement, final String value){
        $("#top-links").scrollTo();
        $(customElement).click();
        $(byXpath(String.format("//li//span[.='%s']", value))).scrollTo().click();
    }

    @Step
    public User fillCity(final String value){
        if(useComponent){
            $(formAddress).$(this.fieldCity).setValue(value);
        } else {
            $(this.fieldCity).setValue(value);
        }
        return this;
    }

    @Step
    public static User loginUserViaJS(final String login, final String password){
        new JSExecutor().POSTWithParams("/user/login/", String.format("{'login[username]':'%s', 'login[password]':'%s'}", login, password));
        return new User();
    }

    @Step
    public static void setCountryUSAForUserViaJS(){
        AccountPersonalData.openPersonalDataPage();
        String csrf = $("[name='YII_CSRF_TOKEN']").getValue();
        new JSExecutor().POSTWithParams("/account/data/", "{'account[countryId]':'840', 'account[stateId]':'-1', 'YII_CSRF_TOKEN':'"+csrf+"'}");
    }

    @Step
    public static void setCountrySpainForUserViaJS(){
        AccountPersonalData.openPersonalDataPage();
        String csrf = $("[name='YII_CSRF_TOKEN']").getValue();
        new JSExecutor().POSTWithParams("/account/data/", "{'account[countryId]':'724', 'account[stateId]':'-1', 'YII_CSRF_TOKEN':'"+csrf+"'}");
    }

    @Step
    public void shouldHaveText(By by, String text){
        $(by).$("em").shouldHave(Condition.text(text));
    }

}
