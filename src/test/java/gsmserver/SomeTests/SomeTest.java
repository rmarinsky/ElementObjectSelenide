
package gsmserver.SomeTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Ignore;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

public class SomeTest {

    @Ignore
    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10000;
        Configuration.collectionsTimeout = 20000;
        open("http://rc.gsmserver.com/cellphones/");
        $(".mobile-switcher a").click();
        for(int i=0;;i++){
            int count = $$(".product-item").size();
            sleep(300);
            $(".show-more-wrapper a").shouldBe(Condition.visible).scrollTo();
            $(".show-more-wrapper a").shouldBe(Condition.visible).scrollTo();
            System.out.println("Iter" + i +" SIZE " + count);
            $(".show-more-wrapper a").shouldBe(Condition.visible).click();
            $$(".product-item").shouldHave(CollectionCondition.sizeGreaterThan(count));
        }
    }
}
