package gsmserver.Components;

import com.codeborne.selenide.Condition;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Cart{

    private String productArg;

    private Cart(){
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
    @Step
    public void cartShouldHaveProduct(Integer id){
        Product.findProduct(id, "tr").shouldBe(Condition.visible);
    }

}
