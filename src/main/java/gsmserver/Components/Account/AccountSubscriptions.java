package gsmserver.Components.Account;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AccountSubscriptions {

    private By subscriptionView = byId("subscription-view");

    public AccountSubscriptions(){
        $(subscriptionView).shouldBe(Condition.visible);
    }

    public static AccountSubscriptions openAccountSubscriptionsPage(){
        open("/account/subscription/");
        return new AccountSubscriptions();
    }

    @Step
    public void shouldBeSelectedSubscriptionByIndex(int i){
        $(subscriptionView).$("label.styled-checkbox", i).has(Condition.attribute("data-selected", "1"));
        $(subscriptionView).$("div.form-summary").has(Condition.cssClass("success"));
    }

}
