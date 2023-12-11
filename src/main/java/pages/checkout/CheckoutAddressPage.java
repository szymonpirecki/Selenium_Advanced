package pages.checkout;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

@Getter
public class CheckoutAddressPage extends BasePage {

    public CheckoutAddressPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[data-link-action='different-invoice-address']")
    WebElement differentInvoiceAddressLink;
    @FindBy(css = "input[name='address1']")
    WebElement addressInput;
    @FindBy(css = "input[name='postcode']")
    WebElement postCodeInput;
    @FindBy(css = "input[name='city']")
    WebElement cityInput;
    @FindBy(css = "select[name='id_country']")
    WebElement countrySelect;
    @FindBy(css = "button[name='confirm-addresses']")
    WebElement confirmAddressButton;

    public CheckoutAddressPage clickDifferentInvoiceAddressLink() {
        clickOnBtn(differentInvoiceAddressLink);
        return this;
    }

    public CheckoutAddressPage provideAddress(String address, String postCode, String city, String country) {
        clearAndSendKeys(addressInput, address);
        clearAndSendKeys(postCodeInput, postCode);
        clearAndSendKeys(cityInput, city);
        Select select = new Select(countrySelect);
        select.selectByVisibleText(country);
        return this;
    }

    public void clickConfirmAddressBtn() {
        clickOnBtn(confirmAddressButton);
    }

}