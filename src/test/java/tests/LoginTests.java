package tests;

import org.openqa.selenium.JavascriptExecutor;
import pages.pages.LoginPage;
import pages.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.TestDataProvider;

public class LoginTests extends BaseTest{
    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class, description = "TC-99 to TC-113 Login scenarios")
    public void testValidLogin(String username, String password, String expected, String tcId) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // Attach TC-ID to BrowserStack session
        ((JavascriptExecutor) driver).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"running\", \"reason\":\""+tcId+"\"}}");

        if (expected.equals("success")) {
            ProductsPage productsPage = new ProductsPage(driver);
            Assert.assertTrue(productsPage.isProductsHeaderDisplayed(), "Products page should be visible");
        } else {
            Assert.assertTrue(loginPage.getErrorMessage().contains(expected), "Error message should match");
        }
    }
}
