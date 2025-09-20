package utils;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] loginData (){
        return new Object[][]{
        {"standard_user", "secret_sauce", "success", "TC-99"},
        {"invalid_user", "secret_sauce", "Username and password do not match", "TC-111"},
        {"standard_user", "wrong_pass", "Username and password do not match", "TC-112"},
        {"wrong_user", "wrong_pass", "Username and password do not match", "TC-113"}
    };
}
    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return new Object[][] {
                {"John", "Doe", "12345", "success", "TC-104"},
                {"", "Doe", "12345", "Error: First Name is required", "TC-108"},
                {"John", "", "12345", "Error: Last Name is required", "TC-109"},
                {"John", "Doe", "", "Error: Postal Code is required", "TC-107"}
        };
    }
}
