package gsmserver.Helpers.JS;

class JSUserHelper extends JavaScriptExecutor {

    /**
     * Авторизовать пользователя с помощью выполнения POST запроса на ссылку: System.getProperty("selenide.baseUrl") + TestData.urlLogin
     * <p>Как правило ссылка будет иметь вид: "http://gsmserver.com/user/login/"</p>
     * @param login
     * Логин пользовательского аккаунта указанный при его регистрации
     * @param password
     * Пароль пользовательского аккаунта указанный при его смене
     * @return
     * Возвращает объект UserHelper (самого себя)
     */
    public JSUserHelper loginUser(final String login, final String password){
        super.sendPOSTRequestWithParams("/user/login/", String.format("{'login[username]':'%s', 'login[password]':'%s'}", login, password));
        return this;
    }

    public JSUserHelper setCountryUSAForUser(){
        super.sendPOSTRequestWithParams("/account/data/", "{'account[countryId]':'840', 'account[stateId]':'-1'}");
        return this;
    }

    public JSUserHelper setCountrySpainForUser(){
        super.sendPOSTRequestWithParams("/account/data/", "{'account[countryId]':'724', 'account[stateId]':'-1'}");
        return this;
    }

}
