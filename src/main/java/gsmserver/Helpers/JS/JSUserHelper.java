package gsmserver.Helpers.JS;

class JSUserHelper extends JavaScriptExecutor {

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
