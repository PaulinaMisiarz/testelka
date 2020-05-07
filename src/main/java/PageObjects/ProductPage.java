package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By addToCartButton = By.cssSelector("button[name='add-to-cart']");
    private By viewCartButton = By.cssSelector(".woocommerce-message>.button");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductPage goTo(String productUrl) {
        driver.navigate().to(productUrl);
        return new ProductPage(driver);
    }

    public ProductPage addToCart() {
        WebElement addButton = driver.findElement(addToCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
        addButton.click();
        wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        return new ProductPage(driver);
    }

    public CartPage viewCart() {
        wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
        return new CartPage(driver);
    }

    public ProductPage addToCartWithQty(int quantity) {
        WebElement quantityField = driver.findElement(By.cssSelector("input.qty"));
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));
        addToCart();
        return new ProductPage(driver);

    }
}
