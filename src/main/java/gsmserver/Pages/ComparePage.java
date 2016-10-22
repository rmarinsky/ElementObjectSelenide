package gsmserver.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static gsmserver.Components.ProductList.getFirstProductId;

public final class ComparePage {

    public ComparePage(){
        $("div.comparison-table").shouldBe(Condition.visible);
    }

    public ComparePage checkPageHaveProducts(){
        $$("td.horizontal-title").shouldHaveSize(2);
        $(byAttribute("data-product", getFirstProductId())).is(Condition.visible);
        $(byAttribute("data-product", getFirstProductId())).is(Condition.visible);
        return this;
    }

    public void checkProductsImagesAndRemoveIt(){
        $$("div.comparison-table img").forEach(SelenideElement::isImage);
        $("a.x-small.remove-product").click();
        $("a.x-small.remove-product").click();
        $("[data-product]").is(Condition.hidden);
    }

}
