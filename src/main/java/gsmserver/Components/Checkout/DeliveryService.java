package gsmserver.Components.Checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DeliveryService{

    @Step("Choose delivery service: [{0}] and check cost")
    public DeliveryService chooseDeliveryServiceAndCheckCost(final String value){
        $("[data-sm-role='checkout.delivery-select']").shouldBe(Condition.visible);
        SelenideElement lineDeliveryCompany = $$("[data-sm-role='checkout.delivery-select']").findBy(Condition.text(value));
        String cost = lineDeliveryCompany.$("td>strong").getText();
        lineDeliveryCompany.click();
        $("div.price-delivery strong").has(Condition.text(cost));
        return this;
    }

}
