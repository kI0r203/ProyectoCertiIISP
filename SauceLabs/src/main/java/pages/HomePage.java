package pages;

import com.google.common.collect.Ordering;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;

import java.time.Duration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    WebDriver driver;
    @FindBy(className="app_logo")
    WebElement logo;

    @FindBy(className = "product_sort_container")
    WebElement sortComboBox;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartContador;

    @FindBy(className = "shopping_cart_link")
    WebElement cart;

    @FindBy(className="bm-item-list")
    WebElement wrapMenu;

    @FindBy(id="react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(xpath = "//*[@id=\"page_wrapper\"]/footer/ul/li[2]/a")
    WebElement  facebookLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageTitleDisplayed() {
        return logo.isDisplayed();
    }

    public void selectComboBox(String option) {
        Select selectObject = new Select(sortComboBox);
        selectObject.selectByVisibleText(option);
    }

    public void selectMenuBox(String option) {
        Select selectObject = new Select(sortComboBox);
        selectObject.selectByVisibleText(option);
    }

    public boolean areProductsInDescendantOrderByName() {
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));

        List<String> actualProductOrder = new ArrayList<>();

        for(WebElement element: products){
            actualProductOrder.add(element.getText());
        }

        return Ordering.natural().reverse().isOrdered(actualProductOrder);
    }

    public boolean areProductsInOrderByName() {
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));

        List<String> actualProductOrder = new ArrayList<>();

        for(WebElement element: products){
            actualProductOrder.add(element.getText());
        }

        return Ordering.natural().isOrdered(actualProductOrder);
    }

    public boolean areProductsInOrderByPrice() {
        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));

        List<Double> actualProductOrder = new ArrayList<>();

        for(WebElement element: products){
            Double precio = Double.parseDouble(element.getText().replace("$", ""));
            actualProductOrder.add(precio);

        }

        return Ordering.natural().reverse().isOrdered(actualProductOrder);
    }

    public boolean areProductsInDescendantOrderByPrice() {
        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));

        List<Double> actualProductOrder = new ArrayList<>();

        for(WebElement element: products){
            Double precio = Double.parseDouble(element.getText().replace("$", ""));
            actualProductOrder.add(precio);

        }

        return Ordering.natural().isOrdered(actualProductOrder);
    }


    public void addProductToCart(String productName){
        //sauce-labs-fleece-jacket
        // id button = add-to-cart-sauce-labs-fleece-jacket
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String addToCartId = "add-to-cart-";
        addToCartId = addToCartId + productNameLowerCase;

        //WebElement addToCartButton = driver.findElement(By.id(addToCartId));
        WebElement addToCartButton = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id(addToCartId)));
        addToCartButton.click();
    }

    public boolean isDisplayedCartBadge() {
        return cartContador.isDisplayed();
    }

    public void logOutClick () {
        menuButton.click();

        Duration duracion = Duration.ofSeconds(1);
        WebDriverWait wait = new WebDriverWait(driver, duracion);
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        //WebElement logoutButton = wrapMenu.findElement(By.linkText("Logout"));
        //System.out.println("Hola");
        //System.out.println("Text is:" + logoutButton.getText());
        logoutButton.click();
        //wait.until(ExpectedConditions.urlContains("https://www.saucedemo.com/"));

    }

    public void cartClick() {
        cart.click();
    }

    public boolean correctLinkFromFacebook (String link) {
        return facebookLink.getAttribute("href").equals(link);
    }

    public void goToFacebook() {
        facebookLink.click();
    }

}
