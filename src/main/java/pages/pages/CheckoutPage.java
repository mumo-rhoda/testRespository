package pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private WebDriver driver;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement zipCode;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMsg;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    @FindBy(className = "complete-header")
    private WebElement orderConfirmation;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCheckoutDetails(String fName, String lName, String zip) {
        firstName.clear();
        firstName.sendKeys(fName);
        lastName.clear();
        lastName.sendKeys(lName);
        zipCode.clear();
        zipCode.sendKeys(zip);
    }

    public void clickContinue() {
        continueBtn.click();
    }

    public String getErrorMessage() {
        return errorMsg.getText();
    }

    public void completeOrder() {
        finishBtn.click();
    }

    public boolean isOrderConfirmed() {
        return orderConfirmation.isDisplayed();
    }
}
