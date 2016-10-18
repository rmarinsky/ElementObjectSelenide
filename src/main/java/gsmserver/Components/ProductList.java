package gsmserver.Components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProductList {

    ProductList(){}

    private final SelenideElement searchTitle = $(".search-title-highlight");

    @Step
    public ProductList openProductListPage(){
        open("/gsm/boxes-and-dongles/");
        return new ProductList();
    }

    /**
     * Search result page should have searched phrase in a title
     */
    @Step
    public ProductList searchResultShouldHasSearchedPhrase(String searchedPhrase){
        searchTitle.shouldHave(Condition.text(searchedPhrase+"dssassaas"));
        return this;
    }

    @Step("Page should have product with id: [{0}]")
    public void shouldHaveProduct(int id){
        Product.findProduct(id, "li").shouldBe(Condition.visible);
    }

    @Step("Page should have no product with id: [{0}]")
    public void shouldHaveNoProduct(int id){
        Product.findProduct(id, "li").shouldNotBe(Condition.visible);
    }

}
