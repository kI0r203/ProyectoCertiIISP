import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.BuyPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class HomeTests extends BaseTest {

    @Test
    public void orderingFromZToA() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.selectComboBox("Name (Z to A)");
        Assertions.assertTrue(homePage.areProductsInDescendantOrderByName());
    }

    @Test
    public void orderingFromAToZ() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.selectComboBox("Name (A to Z)");
        Assertions.assertTrue(homePage.areProductsInOrderByName());
    }

    @Test
    public void orderingFromPriceHighToLow() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.selectComboBox("Price (high to low)");
        Assertions.assertTrue(homePage.areProductsInOrderByPrice());
    }

    @Test
    public void orderingFromPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.selectComboBox("Price (low to high)");
        Assertions.assertTrue(homePage.areProductsInDescendantOrderByPrice());
    }

    @Test
    public void valueOfCartIncrease(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);

        HomePage product = new HomePage(DriverManager.getDriver().driver);
        product.addProductToCart("Sauce Labs Backpack");

        HomePage cartCount = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(cartCount.isDisplayedCartBadge());

    }

    @Test
    public void buttonLogOut() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.logOutClick();
        Assertions.assertTrue(loginPage.isLogoDisplayed());
    }

}
