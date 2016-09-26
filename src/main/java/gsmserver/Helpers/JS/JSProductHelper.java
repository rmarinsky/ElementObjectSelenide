package gsmserver.Helpers.JS;

/**
 * Помошник для отправки ajax запросов к серверу с помощью jQuery методов в Selenide.executeJavaScript()
 */
public class JSProductHelper extends JavaScriptExecutor {

    public JSProductHelper addProductToCart(final Integer productId, Integer count){
        super.sendSimpleGETRequest(String.format("/ajax/cart/add/%s,%s", productId, count));
        return this;
    }

    public JSProductHelper addProductsToCart(Integer... productIds){
        for(Integer id : productIds) {
            this.addProductToCart(id, 1);
        }
        return this;
    }

}
