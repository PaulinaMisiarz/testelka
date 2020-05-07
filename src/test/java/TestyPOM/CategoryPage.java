package TestyPOM;

import PageObjects.CartPage;
import PageObjects.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {

    private WebDriver driver;
    public CategoryPage(WebDriver driver) {
        this.driver = driver;

    }

    public CategoryPage goTo(String url) {
        driver.navigate().to(url);
        return new CategoryPage(driver);
    }

    public CategoryPage addToCart(String productId) {
        By addToCartButton = By.cssSelector(".post-"+productId+">.add_to_cart_button");
        driver.findElement(addToCartButton).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.attributeContains(addToCartButton, "class", "added"));
        return new CategoryPage(driver);

    }

    public CartPage viewCart() {
        By viewCartButton = By.cssSelector(".added_to_cart");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
        return new CartPage(driver);
    }
}
