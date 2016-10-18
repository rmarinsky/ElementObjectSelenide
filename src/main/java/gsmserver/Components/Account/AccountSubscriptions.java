package gsmserver.Components.Account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AccountSubscriptions {

    private SelenideElement subscriptionView = $("#subscription-view");

    public AccountSubscriptions(){
        $(subscriptionView).shouldBe(Condition.visible);
    }

    public static AccountSubscriptions openAccountSubscriptionsPage(){
        open("/account/subscription/");
        return new AccountSubscriptions();
    }

    @Step("Should be selected subscription by index:: [{0}]")
    public void shouldBeSelectedSubscriptionByIndex(int i){
        this.subscriptionView.$("label.styled-checkbox", i).has(Condition.attribute("data-selected", "1"));
        this.subscriptionView.$("div.form-summary").has(Condition.cssClass("success"));
    }

}
