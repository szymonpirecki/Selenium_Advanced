package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LogInPage extends BasePage {
    public LogInPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = ".login-form input[type='email']")
    private WebElement emailInput;

    @FindBy(css = ".login-form input[type='password']")
    private WebElement passwordInput;

    @FindBy(css = "#submit-login")
    private WebElement signInBtn;

    public void logIn(String email, String password){
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        click(signInBtn);
    }

}
