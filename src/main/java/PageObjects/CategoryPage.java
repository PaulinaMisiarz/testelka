package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage extends BasePage {
    private WebDriverWait wait;
    public FooterPage footer;

    public CategoryPage(WebDriver driver) {
        super(driver);
        footer = new FooterPage(driver);
        wait = new WebDriverWait(driver, 5);
    }

    private  By viewCartButtonLocator = By.cssSelector(".added_to_cart");
    private String addToCartButtonCssSelector = ".post-<productId>>.add_to_cart_button";

    public CategoryPage goTo(String url) {
        driver.navigate().to(url);
        return new CategoryPage(driver);
    }

    public CategoryPage addToCart(String productId) {
        By addToCartButton = By.cssSelector(addToCartButtonCssSelector.replace("<productId>", productId));
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.attributeContains(addToCartButton, "class", "added"));
        return new CategoryPage(driver);

    }

    public CartPage viewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator)).click();
        return new CartPage(driver);
    }
}
