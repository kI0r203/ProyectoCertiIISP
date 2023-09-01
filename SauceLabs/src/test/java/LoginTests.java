import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class LoginTests extends BaseTest{

    @Test
    public void loginSuccesTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(homePage.pageTitleDisplayed());
    }

    @Test
    public void loginFailedTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("wrong_user");
        loginPage.setPasswordTextBox("wrong_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(loginPage.isErrorTextDisplayed("Epic sadface: Username and password do not match any user in this service"));
    }
}
