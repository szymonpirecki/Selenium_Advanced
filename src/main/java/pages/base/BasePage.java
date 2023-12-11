package pages.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Random;

@Slf4j
public class BasePage {

    public WebDriver driver;
    public WebDriverWait defaultWait;
    public Actions actions;
    public Random random;

    public BasePage(WebDriver driver) {
        init(driver);
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver, WebElement parent) {
        init(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(parent), this);
    }

    private void init(WebDriver driver) {
        this.driver = driver;
        defaultWait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("explicitWaitTimeout"))));
        actions = new Actions(driver);
        random = new Random();
    }


    public void clickOnBtn(WebElement button) {
        defaultWait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void waitForElement(WebElement element) {
        defaultWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAllElements(List<WebElement> elements) {
        defaultWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void clearAndSendKeys(WebElement input, String text) {
        defaultWait.until(ExpectedConditions.visibilityOf(input));
        input.click();
        input.clear();
        input.sendKeys(text);
    }

    public BigDecimal getBigDecimal(WebElement element) {
        waitForElement(element);
        return new BigDecimal(element.getText().replaceAll("[^0-9.]", ""));
    }

    public int getInt(WebElement webElement) {
        waitForElement(webElement);
        return Integer.parseInt(webElement.getText().replaceAll("[^0-9]", ""));
    }

    public int getValue(WebElement webElement) {
        waitForElement(webElement);
        return Integer.parseInt(webElement.getAttribute("value"));
    }
}
