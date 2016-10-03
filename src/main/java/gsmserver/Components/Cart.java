package gsmserver.Components;

import com.codeborne.selenide.Condition;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Cart {

    private String productArg;

    public Cart(){
        $("#cart").shouldBe(Condition.visible);
    }

    @Step
    public Cart openCartPage(){
        open("/cart/");
        return this;
    }

    /**
     * Find product in table by product id
     * @param id
     * id of product (Example: 856333)
     */
    @Step
    public void cartShouldHaveProduct(Integer id){
        Product.findProduct(id, "tr").shouldBe(Condition.visible);
    }

}
