import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.BuyPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class BuyTest extends BaseTest {

    @Test
    public void makeABuy() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.cartClick();

        BuyPage buyPage = new BuyPage(DriverManager.getDriver().driver);
        buyPage.buy("aa","aa","aa");
        buyPage.finishBuy();
        Assertions.assertTrue(buyPage.isCompleteBuy());
    }

    @Test
    public void makeABuyWithoutFirstName() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.cartClick();

        BuyPage buyPage = new BuyPage(DriverManager.getDriver().driver);
        buyPage.buy("","aa","aa");
        Assertions.assertTrue(buyPage.isFailedBuy("Error: First Name is required"));
    }
}
