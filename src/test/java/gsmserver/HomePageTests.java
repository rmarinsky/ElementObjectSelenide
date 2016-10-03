package gsmserver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Components.HomePage;
import gsmserver.Utils.BaseTest;
import org.junit.Test;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.*;
import static gsmserver.Components.Product.addToCartButtons;
import static org.junit.Assert.fail;

public class HomePageTests extends BaseTest{

    @Test public void bannersShouldBeVisibleTests(){
        new HomePage().bannersShouldBeVisible();
    }

    @Test public void testsForCategories(){
        for(SelenideElement element : $$("#main-menu a.mainmenu-item-link").excludeWith(Condition.cssClass("sale"))) {
            open(element.getAttribute("href"));
            $(".promoblock").shouldBe(Condition.visible);
            $(".promoblock").$$("img").forEach(SelenideElement::isImage);
            addToCartButtons.forEach(SelenideElement::exists);
        }
    }

    @Test public void testsForSaleAndBenefitsPages(){
        $("td.last.sale").click();
        $$("li.promotion-item img").forEach(SelenideElement::isImage);
        open($(".header-orange-link").getAttribute("href"));
        $("a.button-chat").shouldBe(Condition.visible);
        try {
            if($(".button-download").download().length() < 100000) fail("Price list too little");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        $("[name='benefit[name]']").setValue("testName");
        $("[name='benefit[email]']").setValue("testemail@mail.com");
        $("[name='benefit[comment]']").setValue("comment");
        $("a.button-send").click();
        $(".g-recaptcha").shouldNotBe(Condition.hidden);
    }

    @Test public void infoBlockHavImagesTest(){
        new HomePage().infoBlockHaveImages();
    }

    @Test public void addingToCartProductTest(){
        new HomePage().addToCartFirstProduct().
                cartIconHaveCount();
    }

}
