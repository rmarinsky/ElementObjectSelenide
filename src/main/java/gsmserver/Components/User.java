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

    private By componentElement = byCssSelector("form[name='address']");

    private final By byLogin = byCssSelector("input[name*='username']"),
            byPassword = byCssSelector("input[name*='password']"),
            byPasswordConfirm = byCssSelector("input[name*='password_confirm']"),
            byFirstName = byCssSelector("input[name*='firstName']"),
            byEmail = byCssSelector("input[name*='email']"),
            byLastName = byCssSelector("input[name*='lastName']"),
            byCity = byCssSelector("input#city"),
            byMiddleName = byCssSelector("input[name*='middleName']"),
            byCustomCountry = byCssSelector("#country_id_chosen"),
            byCustomRegion = byCssSelector("#state_id_chosen"),
            byCustomCity = byCssSelector("#city_id_chosen"),
            byAddress = byCssSelector("input[name*='[address]']"), //because of element have name = "address[address]"
            byPhoneCountries = byCssSelector("a.styled-phone-dropdown-button"),
            byPhoneNumber = byCssSelector(".styled-phone-edit > input"),
            byBirthday = byCssSelector("#birthday"),
            byZip = byCssSelector("input[name*='zip'"),
            byTaxId = byCssSelector("input[name*='taxId']");

    public final SelenideElement
            fieldLogin = $(byLogin),
            fieldPassword = $(byPassword),
            fieldPasswordConfirm = $(byPasswordConfirm),
            fieldFirstName = $(byFirstName),
            fieldEmail = $(byEmail),
            fieldLastName = $(byLastName),
            fieldMiddleName = $(byCity),
            customCountry = $(byMiddleName),
            customRegion = $(byCustomCountry),
            customCity = $(byCustomRegion),
            fieldCity = $(byCustomCity),
            fieldAddress = $(byAddress), //because of element have name = "address[address]"
            customPhoneCountries = $(byPhoneCountries),
            fieldPhoneNumber = $(byPhoneNumber),
            birthday = $(byBirthday),
            fieldZip = $(byZip),
            fieldTaxId = $(byTaxId);

    public User(){

    }

    public User(boolean useComponent){
        this.useComponent = useComponent;
    }

    @Step
    public User fillLogin(final String value){
        this.fieldLogin.sendKeys(value);
        return this;
    }

    @Step
    public User fillPassword(final String value){
        this.fieldPassword.setValue(value);
        return this;
    }

    @Step
    public User fillConfirmPassword(final String value){
        this.fieldPasswordConfirm.setValue(value);
        return this;
    }

    @Step
    public User fillFirstName(final String value){
        if(useComponent){
            $(componentElement).$(this.byFirstName).setValue(value);
        } else {
            this.fieldFirstName.setValue(value);
        }
        return this;
    }

    @Step
    public User fillLastName(final String value) {
        if(useComponent){
            $(componentElement).$(this.byLastName).setValue(value);
        } else {
            this.fieldLastName.setValue(value);
        }
        return this;
    }

    @Step
    public User fillMiddleName(final String value) {
            if(useComponent){
                $(componentElement).$(this.byMiddleName).setValue(value);
            } else {
                this.fieldMiddleName.setValue(value);
            }
        return this;
    }

    @Step
    public User fillEmail(final String value) {
        if(useComponent){
            $(componentElement).$(this.byEmail).setValue(value);
        } else {
            this.fieldEmail.setValue(value);
        }
        return this;
    }

    @Step
    public User fillAddress(final String value) {
        if(useComponent){
            $(componentElement).$(this.byAddress).setValue(value);
        } else {
            this.fieldAddress.setValue(value);
        }
        return this;
    }


    @Step
    public User fillPhoneByCountry(final String country, final String phone){
        this.customPhoneCountries.click();
        $(String.format("b[data-hint='%s']", country)).scrollTo().click();
        this.fieldPhoneNumber.setValue(phone);
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
            $(componentElement).$(this.byCity).setValue(value);
        } else {
            this.fieldCity.setValue(value);
        }
        return this;
    }

    private void selectItem(String nameOfItem){
        $(byXpath(String.format("//li//span[.='%s']", nameOfItem))).scrollTo().click();
    }

    @Step
    public User loginUserViaJS(final String login, final String password){
        new JSExecutor().POSTWithParams("/user/selctorLogin/", String.format("{'selctorLogin[username]':'%s', 'selctorLogin[password]':'%s'}", login, password));
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
