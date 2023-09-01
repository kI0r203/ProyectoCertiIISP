import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.BuyPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

import java.util.Set;

public class Proyect extends BaseTest{
    @Test
    public void valueOfCartIncrease(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        Assertions.assertTrue(homePage.isDisplayedCartBadge());
    }

    @Test
    public void makeABuy() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
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

    @Test
    public void footerFacebookLink() {
            //https://www.facebook.com/saucelabs
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);

        //Assertions.assertTrue(homePage.correctLinkFromFacebook("https://www.facebook.com/saucelabs"));
        homePage.goToFacebook();
        String facebook = "";

        String mainTab = DriverManager.getDriver().driver.getWindowHandle();
        Set<String> allTabs = DriverManager.getDriver().driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(mainTab)) {
                DriverManager.getDriver().driver.switchTo().window(tab);
                facebook = DriverManager.getDriver().driver.getCurrentUrl();

                //DriverManager.getDriver().driver.close();
            }
        }
        Assertions.assertTrue(facebook.equalsIgnoreCase("https://www.facebook.com/saucelabs"));
    }

    @Test
    public void ceroProductsBought() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.cartClick();
        BuyPage buyPage = new BuyPage(DriverManager.getDriver().driver);
        buyPage.buy("aa","aa","aa");
        String precio = buyPage.getPrice();
        buyPage.finishBuy();
        Assertions.assertFalse(buyPage.isCompleteBuy() && precio.equals("Total: $0.00"));
    }

}
