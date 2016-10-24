package gsmserver.Components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static gsmserver.Components.Product.findProduct;

public class ProductList {

    private static String firstProductId;
    private static String secondProductId;

    public static String getFirstProductId() {
        return firstProductId;
    }

    public static String getSecondProductId() {
        return secondProductId;
    }

    private SelenideElement searchTitle = $(".search-title-highlight");
    private SelenideElement paginationTop = $(".pagination-wrapper-top");
    private SelenideElement paginationBottom = $(".pagination-wrapper-bottom");

    private By checkboxAddToComparison = Selectors.byCssSelector("span.styled-checkbox");
    private By buttonNext = Selectors.byCssSelector(".button-arrow.next");
    private By buttonPrev = Selectors.byCssSelector(".button-arrow.prev");
    private By wishListStar = Selectors.byCssSelector(".ga-add-to-wishlist");
    private By wishListBlock = Selectors.byCssSelector(".wish-list-block");

    public ProductList(){
        $(".title-long-text-wrapper").shouldBe(Condition.visible);
    }

    @Step
    public static ProductList openProductListPage(){
        open("/gsm/boxes-and-dongles/");
        return new ProductList();
    }

    /**
     * Search result page should have searched phrase in a title
     */
    @Step("Search result page should has searched phrase: [{0}]")
    public ProductList searchResultShouldHasSearchedPhrase(String searchedPhrase){
        this.searchTitle.shouldHave(Condition.text(searchedPhrase));
        return this;
    }

    @Step("Page should have product with id: [{0}]")
    public void shouldHaveProduct(int id){
        findProduct(id).shouldBe(Condition.visible);
    }

    @Step("Page should have no product with id: [{0}]")
    public void shouldHaveNoProduct(int id){
        findProduct(id).shouldNotBe(Condition.visible);
    }

    @Step("Add first two products to comparison")
    public void addToComparisonProducts(){
        $(this.checkboxAddToComparison, 0).shouldNotHave(Condition.attribute("data-selected","on"));
        $(this.checkboxAddToComparison, 1).shouldNotHave(Condition.attribute("data-selected","on"));
        $(this.checkboxAddToComparison, 0).click();
        $(this.checkboxAddToComparison, 1).click();
        $(this.checkboxAddToComparison, 0).shouldHave(Condition.attribute("data-selected","on"));
        $(this.checkboxAddToComparison, 1).shouldHave(Condition.attribute("data-selected","on"));
        $(this.checkboxAddToComparison, 0).click();
        $(this.checkboxAddToComparison, 1).click();
        $(this.checkboxAddToComparison, 0).shouldNotHave(Condition.attribute("data-selected","on"));
        $(this.checkboxAddToComparison, 1).shouldNotHave(Condition.attribute("data-selected","on"));
        $(this.checkboxAddToComparison, 0).click();
        $(this.checkboxAddToComparison, 1).click();

        firstProductId = $(this.checkboxAddToComparison, 0).parent().getAttribute("data-id");
        secondProductId = $(this.checkboxAddToComparison, 1).parent().getAttribute("data-id");
    }

    @Step
    public void goingByPagesAndReturnBackToFirstPage(){
        this.paginationTop.shouldBe(Condition.visible);
        this.paginationBottom.shouldBe(Condition.visible);
        this.paginationTop.$(buttonNext).click();
        this.currentPageIs(2);
        this.paginationBottom.$("[data-value='3']").scrollTo().click();
        this.currentPageIs(3);
        this.paginationBottom.$(buttonNext).scrollTo().click();
        this.currentPageIs(4);
        this.paginationBottom.$(buttonPrev).scrollTo().click();
        this.currentPageIs(3);
        this.paginationBottom.$(".pagination-range-wrapper [data-value='1']").scrollTo().click();
        this.currentPageIs(1);
        this.paginationBottom.$(buttonPrev).shouldBe(Condition.hidden);
        this.paginationBottom.$(buttonNext).shouldBe(Condition.visible);
        this.paginationBottom.$("[data-pagination='page-last']").click();
        this.paginationBottom.$(buttonPrev).shouldBe(Condition.visible);
        this.paginationBottom.$(buttonNext).shouldBe(Condition.hidden);
        this.paginationBottom.$("[data-pagination='page-first']").click();
        this.paginationBottom.$(buttonPrev).shouldBe(Condition.hidden);
        this.paginationBottom.$(buttonNext).shouldBe(Condition.visible);
    }

    @Step
    private void currentPageIs(int pageNumber){
        this.paginationTop.$("[data-pagination='display-page']").shouldHave(Condition.text(String.valueOf(pageNumber)));
        if(pageNumber > 1) this.paginationBottom.$("[data-value='"+String.valueOf(pageNumber-1)+"']").shouldNotHave(Condition.cssClass("current"));
        this.paginationBottom.$(".current").shouldHave(Condition.attribute("data-value",String.valueOf(pageNumber)));
    }

    @Step
    public Integer addToWishListProduct(){
        String productId = $(".product-list-wrapper [data-product-id]").getAttribute("data-product-id");
        Product.findProduct(Integer.valueOf(productId)).$(wishListStar).click();
        return Integer.valueOf(productId);
    }

    @Step
    public ProductList wishListStarCheckForProduct(Integer productId){
        Product.findProduct(productId).$(wishListBlock).shouldHave(Condition.cssClass("active"));
        Product.findProduct(productId).$("[href*='/wishlist/remove/']").click();
        Product.findProduct(productId).$(wishListBlock).shouldNotHave(Condition.cssClass("active"));
        Product.findProduct(productId).$(wishListStar).click();
        Product.findProduct(productId).$(wishListBlock).shouldHave(Condition.cssClass("active"));
        return this;
    }

    @Step
    public void openWishListPageViaProduct(Integer productId){
        Product.findProduct(productId).$("[href='/account/wishlist/']").click();
    }

}
