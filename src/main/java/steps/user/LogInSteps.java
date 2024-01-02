package steps.user;

import org.openqa.selenium.WebDriver;
import pages.login.LogInPage;
import providers.UrlProvider;
import steps.base.BaseSteps;

public class LogInSteps extends BaseSteps {
    public LogInSteps(WebDriver driver) {
        super(driver);
    }

    public LogInSteps goToLoginPage(){
        driver.get(UrlProvider.LOGIN_URL.getUrl());
        return this;
    }

    public AccountSteps logInUser(String email, String password){
        at(LogInPage.class).logIn(email, password);
        return new AccountSteps(driver);
    }
}
