package pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    private WebDriver driver;

    @FindBy(className = "title")
    private WebElement productsHeader;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackBtn;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductsHeaderDisplayed() {
        return productsHeader.isDisplayed();
    }

    public void addBackpackToCart() {
        addBackpackBtn.click();
    }

    public String getCartCounter() {
        return cartBadge.getText();
    }

    public void goToCart() {
        cartLink.click();
    }
}
