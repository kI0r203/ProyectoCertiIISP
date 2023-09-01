package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    @FindBy(id="user-name")
    WebElement userNameTextBox;

    @FindBy(id="password")
    WebElement passwordTextBox;

    @FindBy(id="login-button")
    WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")
    WebElement messegeError;

    @FindBy(className="login_logo")
    WebElement loginLogo;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void setUserNameTextBox(String name) {
        userNameTextBox.sendKeys(name);
    }

    public void setPasswordTextBox(String password) {
        passwordTextBox.sendKeys(password);
    }

    public void clickLoginButton (){
        loginButton.click();
    }

    public boolean isErrorTextDisplayed (String error) {
        return messegeError.getText().equalsIgnoreCase(error);
    }

    public boolean isLogoDisplayed() {
        return loginLogo.isDisplayed();
    }
}
