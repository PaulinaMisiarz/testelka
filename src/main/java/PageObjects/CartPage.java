package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By shopTableLocator = By.cssSelector(".shop_table");
    private By productQuantityLocator = By.cssSelector("div.quantity>input");
    private String removeProductCssSelector = "a[data-product_id='<product_id>']";
    private By cartItemLocator = By.cssSelector(".cart_item");

    public int getProductQty() {
        waitForShopTable();
        String quantityString = driver.findElement(productQuantityLocator).getAttribute("value");
        return Integer.parseInt(quantityString);
    }

    public boolean isProductInCart(String productId) {
        waitForShopTable();
        By removeProductLocator = By.cssSelector(removeProductCssSelector.replace("<product_id>", productId));
        int productRecords =  driver.findElements(removeProductLocator).size();
        boolean presenceOfProduct = false;
        if(productRecords==1){
            presenceOfProduct = true;
        }else if(productRecords>1){
            throw new IllegalArgumentException("There is more than one record for the product in cart");
        }
        return presenceOfProduct;
    }
    private void waitForShopTable(){
        wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.presenceOfElementLocated(shopTableLocator));
    }

    public int getNumberOfProducts() {
        waitForShopTable();
        return driver.findElements(cartItemLocator).size();
    }
}
