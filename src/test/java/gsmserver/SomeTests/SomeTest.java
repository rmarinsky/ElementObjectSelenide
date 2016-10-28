

package gsmserver.SomeTests;

public class SomeTest {

   /* @Ignore
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
    }*/

    /*@Test
    public void olo(){
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "http://rc.gsmserver.com";
        open("");
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
        }
        Product.addProductSToCartViaJs(ids);
        }*/
    /*@Test
    public void ololo(){
        MarionetteDriverManager.getInstance().setup();
        WebDriverRunner.setWebDriver(new FirefoxDriver());
        open("https://bo.ehopper.com/9/?id=0#login");
        $(".form-signin").sendKeys("1");
        $(".pin-display span.active").shouldBe(Condition.visible);
    }*/

}
