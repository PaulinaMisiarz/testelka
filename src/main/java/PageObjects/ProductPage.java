package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
    public HeaderPage header;
    public FooterPage footer;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        super(driver);
        header = new HeaderPage(driver);
        footer = new FooterPage(driver);
        wait = new WebDriverWait(driver, 7);
    }

    private By addToCartButtonLocator = By.cssSelector("button[name='add-to-cart']");
    private By viewCartButtonLocator = By.cssSelector(".woocommerce-message>.button");
    private By productQuantityLocator = By.cssSelector("input.qty");

    public ProductPage goTo(String productUrl) {
        driver.navigate().to(productUrl);
        return new ProductPage(driver);
    }

    public ProductPage addToCart() {
        WebElement addButton = driver.findElement(addToCartButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
        addButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator));
        return new ProductPage(driver);
    }

    public CartPage viewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator)).click();
        return new CartPage(driver);
    }

    public ProductPage addToCartWithQty(int quantity) {
        WebElement quantityField = driver.findElement(productQuantityLocator);
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));
        addToCart();
        return new ProductPage(driver);

    }
}
