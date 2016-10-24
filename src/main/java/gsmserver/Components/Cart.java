package gsmserver.Components;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Cart{

    private String productArg;

    private By buttonRemoveProduct = byCssSelector(".remove-product a");

    Cart(){
        $("#cart").shouldBe(Condition.visible);
    }

    @Step
    public static Cart openCartPage(){
        open("/cart/");
        return new Cart();
    }

    /**
     * Find product in table by product id
     * @param id
     * id of product (Example: 856333)
     */
    @Step("Cart should have product: [{0}]")
    public Cart cartShouldHaveProduct(Integer id){
        Product.findProduct(id).shouldBe(Condition.visible);
        return this;
    }


    @Step("Keep product [{0}] in cart")
    public Cart keepProductInCart(Integer id){
        Product.findProduct(id).$(buttonRemoveProduct).click();
        $(byName("remove-product-rejected")).click();
        return this;
    }

    @Step("Keep product [{0}] in cart")
    public void removeProductFromCart(Integer id){
        Product.findProduct(id).$(buttonRemoveProduct).click();
        $(byName("remove-product-confirmed")).click();
        Product.findProduct(id).shouldBe(Condition.hidden);
    }

    @Step
    public void shouldBeVisibleMassageLowCost(){
        $("[data-error-type='denied-delivery']").shouldBe(Condition.visible);
    }

}
