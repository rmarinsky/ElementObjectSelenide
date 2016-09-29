package gsmserver.Components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Utils.BaseTest;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static gsmserver.Components.Product.addToCartButtons;
import static gsmserver.Components.Product.inputQuantity;

public class HomePage extends BaseTest{

    private final SelenideElement mainBanner = $("#sm_slider > ul.sm_slider-inner");

    public HomePage bannersShouldBeVisible(){
        this.mainBanner.shouldBe(Condition.visible);
        $("div.side-banner.left-banner").shouldBe(Condition.visible);
        $("div.side-banner.right-banner").shouldBe(Condition.visible);
        return this;
    }

    private void categoryPageCheck(final SelenideElement element){
        open(element.getAttribute("href"));
        $(".promoblock").shouldBe(Condition.visible);
        $(".promoblock").$$("img").forEach(SelenideElement::isImage);
        addToCartButtons.forEach(SelenideElement::exists);
    }

    public void categoriesPagesChecks(){
        this.categoryPageCheck($(".main-menu-item-gsm>a"));
        this.categoryPageCheck($(".main-menu-item-cellphones>a"));
        this.categoryPageCheck($(".main-menu-item-equipment>a"));
        this.categoryPageCheck($(".main-menu-item-cars>a"));
        this.categoryPageCheck($(".main-menu-item-spares>a"));
        this.categoryPageCheck($(".main-menu-item-toys>a"));
        this.categoryPageCheck($(".main-menu-item-other>a"));
    }

    public void infoBlockHaveImages(){
        $(".info-blocks").$$("img").forEach(SelenideElement::isImage);
    }

    @Step
    public HomePage addToCartProduct(){
        addToCartButtons.get(0).click();
        inputQuantity.shouldBe(Condition.visible);
        return this;
    }

    @Step public void cartIconHaveCount(){
        $(".cart-indicator a.cart-icon").shouldHave(Condition.attribute("data-cart-quantity", "1"));
    }

}
