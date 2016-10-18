package gsmserver;

import gsmserver.Components.Search;
import gsmserver.Utils.BaseTest;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;


@Description(value = "Tests for searching on site")
public class SearchTests extends BaseTest {

    @Title(value = "Simple test for search")
    @Test public void searchTest(){
        new Search().searchFor("smart-clip").
                searchResultShouldHasSearchedPhrase("smart-clip");
    }

    @Test
    public void searchByCategoriesTest(){
        new Search().checkSearchByCategories();
    }

}
