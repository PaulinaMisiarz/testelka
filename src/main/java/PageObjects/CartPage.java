package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By shopTable = By.cssSelector(".shop_table");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public Integer getProductAmount(String productId) {
        wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.presenceOfElementLocated(shopTable));
        By removeProductButton = By.cssSelector("a[data-product_id='" + productId + "']");
        return driver.findElements(removeProductButton).size();
    }
}
