package gsmserver;

import com.codeborne.selenide.Condition;
import gsmserver.Components.Popups.AuthorisationPopup;
import gsmserver.Components.Product;
import gsmserver.Components.ProductList;
import gsmserver.Components.RightFixedButtons;
import gsmserver.Components.User;
import gsmserver.Pages.HomePage;
import gsmserver.Utils.BaseTest;
import gsmserver.Utils.DefaultData;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

public class ProductListTests extends BaseTest {

    @BeforeClass
    public static void beforeClass(){
        HomePage.openHomePage();
    }

    @Before
    public void beforeBase(){
        clearCookies();
        ProductList.openProductListPage();
    }

    @Test
    public void addProductToComparisonTest(){
        new ProductList().addToComparisonProducts();
        new RightFixedButtons().openComparePage().
                checkPageHaveProducts().
        checkProductsImagesAndRemoveIt();
    }

    @Test
    public void goingByPagesAndReturnBackToFirstPageTest(){
        new ProductList().goingByPagesAndReturnBackToFirstPage();
    }

    @Test
    public void addingToWishListProductWOUser(){
        Integer productId = new ProductList().addToWishListProduct();
        new AuthorisationPopup().loginUser(DefaultData.defaultEmail, DefaultData.defaultPassword);
        $("#user_product_table_full").shouldBe(Condition.visible);
        $("#user_product_table_full").$("[data-product-id='"+productId+"']").shouldBe(Condition.visible);
        ProductList.openProductListPage().wishListStarCheckForProduct(productId);
        new Product().removeProductFromWishListViaJS(productId);
    }

    @Test
    public void addingToWithListProductViaUser(){
        new User().loginUserViaJS(DefaultData.defaultEmail, DefaultData.defaultPassword);
        refresh();
        Integer productId = new ProductList().addToWishListProduct();
        new ProductList().wishListStarCheckForProduct(productId).
                openWishListPageViaProduct(productId);
        $("#user_product_table_full").shouldBe(Condition.visible);
        $("#user_product_table_full").$("[data-product-id='"+productId+"']").shouldBe(Condition.visible);
        new Product().removeProductFromWishListViaJS(productId);
    }

}
