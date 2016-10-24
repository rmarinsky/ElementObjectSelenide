package gsmserver.Components.Popups;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Components.MainComponent;
import gsmserver.Components.User;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DeliveryCalculation {

    private SelenideElement deliveryCalculationPopup = $("#delivery-calculator-view");

    public DeliveryCalculation(){
        deliveryCalculationPopup.shouldBe(Condition.visible);
    }

    private By storeGroup = byCssSelector("#store_group_id_chosen");

    @Step("Check visible of main elements and calculate price")
    public DeliveryCalculation checkFormAndCalculatePrice(){
        User user = new User();
        user.shouldHaveText(storeGroup, "Hong Kong");
        $("[name*='productCount']").shouldBe(Condition.visible);
        user.chooseCountry("Spain");
        user.chooseRegion("Aragon");
        MainComponent.submitForm();
        $$(".service-wrapper h5").findBy(Condition.text("Sweden Post")).shouldHave(Condition.text("5.66"));
        return this;
    }

    @Step
    public void closeDeliveryCalculationPopup(){
        deliveryCalculationPopup.$("a.close").click();
        deliveryCalculationPopup.shouldBe(Condition.hidden);
    }

}
