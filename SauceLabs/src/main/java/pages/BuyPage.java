package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuyPage {

    WebDriver driver;

    @FindBy(id = "checkout")
    WebElement checkoutButtton;

    @FindBy(id = "first-name")
    WebElement firstNameCheckBox;

    @FindBy(id = "last-name")
    WebElement lastNameCheckBox;

    @FindBy(id = "postal-code")
    WebElement postalCodeCheckBox;

    @FindBy(id="continue")
    WebElement continueButton;

    @FindBy(id="finish")
    WebElement finishButton;

    @FindBy(id="back-to-products")
    WebElement backButton;

    @FindBy(xpath = "//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")
    WebElement messegeError;

    public BuyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void buy(String fname, String lname, String pCode){
        checkoutButtton.click();
        firstNameCheckBox.sendKeys(fname);
        lastNameCheckBox.sendKeys(lname);
        postalCodeCheckBox.sendKeys(pCode);
        continueButton.click();
    }

    public void finishBuy() {
        finishButton.click();
    }

    public boolean isCompleteBuy() {
        return backButton.isDisplayed();
    }

    public boolean isFailedBuy(String mError) {
        return messegeError.getText().equalsIgnoreCase(mError);
    }

    public void removeProduct(String product){
        //sauce-labs-bike-bight
        //remove-
        String productLowerCase = product.toLowerCase();
        productLowerCase = productLowerCase.replace(" ", "-");
        String removeProductId = "remove-"+productLowerCase;
        WebElement removeButton = driver.findElement(By.id(removeProductId));
        removeButton.click();
    }

    public String  getPrice() {

        WebElement totalCompra = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/div[8]"));
        return totalCompra.getText();
    }
}
