package gsmserver.Components;

import com.codeborne.selenide.SelenideElement;
import gsmserver.Utils.JSExecutor;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class User {

    private boolean useComponent = false;

    /*@FindBy(css = "form[name='address']")
    private User componentElement;*/
    /*public User setAddressComponent(){
        componentElement = $("form[name='address']");
        return this;
    }*/
    private By componentElement = byCssSelector("form[name='address']");

    public final SelenideElement
            login = $("input[name*='username']"),
            password = $("input[name*='password']"),
            passwordConfirm = $("input[name*='password_confirm']"),
            firstName = $("input[name*='firstName']"),
            email = $("input[name*='email']"),
            lastName = $("input[name*='lastName']"),
            inputCity = $("input#city"),
            middleName = $("input[name*='middleName']"),
            customCountry = $("#country_id_chosen"),
            customRegion = $("#state_id_chosen"),
            customCity = $("#city_id_chosen"),
            address = $("input[name*='[address]']"), //because of element have name = "address[address]"
            phoneCountries = $("a.styled-phone-dropdown-button"),
            phoneNumber = $("div.styled-phone-edit > input"),
            birthday = $("#birthday"),
            zip = $("input[name*='zip'"),
            taxId = $("input[name*='taxId']");

    public User(){

    }

    public User(boolean useComponent){
        this.useComponent = useComponent;
    }

    @Step
    public User fillLogin(final String value){
        this.login.sendKeys(value);
        return this;
    }

    @Step
    public User fillPassword(final String value){
        this.password.sendKeys(value);
        return this;
    }

    @Step
    public User fillFirstName(final String value){
        if(useComponent){
            $(componentElement).$("input[name*='firstName']").setValue(value);
        } else {
            this.firstName.setValue(value);
        }
        return this;
    }

    @Step
    public User fillLastName(final String value) {
        if(useComponent){
            $(componentElement).$("input[name*='lastName']").setValue(value);
        } else {
            this.lastName.setValue(value);
        }
        return this;
    }

    @Step
    public User fillMiddleName(final String value) {
            if(useComponent){
                $(componentElement).$("input[name*='middleName']").setValue(value);
            } else {
                this.middleName.setValue(value);
            }
        return this;
    }

    @Step
    public User fillEmail(final String value) {
        if(useComponent){
            $(componentElement).$("input[name*='email']").setValue(value);
        } else {
            this.email.setValue(value);
        }
        return this;
    }

    @Step
    public User fillAddress(final String value) {
        if(useComponent){
            $(componentElement).$("input[name*='[address]']").setValue(value);
        } else {
            this.address.setValue(value);
        }
        return this;
    }


    @Step
    public User fillPhoneByCountry(final String country, final String phone){
        this.phoneCountries.click();
        $(String.format("b[data-hint='%s']", country)).scrollTo().click();
        this.phoneNumber.setValue(phone);
        return this;
    }

    @Step
    public User chooseCountry(final String value){
        $("h1").scrollTo();
        this.customCountry.click();
        this.selectItem(value);
        return this;
    }

    @Step
    public User chooseRegion(final String value){
        $("h1").scrollTo();
        this.customRegion.click();
        this.selectItem(value);
        return this;
    }

    @Step
    public User chooseCity(final String value){
        $("h1").scrollTo();
        this.customCity.click();
        this.selectItem(value);
        return this;
    }

    @Step
    public User fillCity(final String value){
        if(useComponent){
            $(componentElement).$("input#city").setValue(value);
        } else {
            this.inputCity.setValue(value);
        }
        return this;
    }

    private void selectItem(String nameOfItem){
        $(byXpath(String.format("//li//span[.='%s']", nameOfItem))).scrollTo().click();
    }

    @Step
    public User loginUserViaJS(final String login, final String password){
        new JSExecutor().POSTWithParams("/user/login/", String.format("{'login[username]':'%s', 'login[password]':'%s'}", login, password));
        return this;
    }

    @Step
    public void setCountryUSAForUserViaJS(){
        open("/account/data/");
        String csrf = $("[name='YII_CSRF_TOKEN']").getValue();
        new JSExecutor().POSTWithParams("/account/data/", "{'account[countryId]':'840', 'account[stateId]':'-1', 'YII_CSRF_TOKEN':'"+csrf+"'}");
    }

    @Step
    public void setCountrySpainForUserViaJS(){
        open("/account/data/");
        String csrf = $("[name='YII_CSRF_TOKEN']").getValue();
        new JSExecutor().POSTWithParams("/account/data/", "{'account[countryId]':'724', 'account[stateId]':'-1', 'YII_CSRF_TOKEN':'"+csrf+"'}");
    }

}
