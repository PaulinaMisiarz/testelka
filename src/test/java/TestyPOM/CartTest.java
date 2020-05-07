package TestyPOM;

import PageObjects.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest {
    WebDriver driver;
    String productUrl = "https://fakestore.testelka.pl/product/egipt-el-gouna/";
    String productId = "386";
    String categoryUrl = "https://fakestore.testelka.pl/product-category/windsurfing/";
    @BeforeEach
    public void testSetUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8,30));
        driver.navigate().to("https://fakestore.testelka.pl");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
    }
    @AfterEach
    public void closeDriver(){
        driver.quit();
    }

    @Test
    public void addToCartFromProductPageTest(){
    ProductPage productPage = new ProductPage(driver);
    int productAmount = productPage.goTo(productUrl).addToCart().viewCart().getProductAmount(productId);
    assertTrue(productAmount==1,
                "Remove button was not found for a product with" + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }

    @Test
    public void addToCartFromCategoryPageTest(){
        CategoryPage categoryPage = new CategoryPage(driver);
        int productAmount = categoryPage.goTo(categoryUrl).addToCart(productId).viewCart().getProductAmount(productId);
        assertTrue(productAmount==1,
                "Remove button was not found for a product with" + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");

    }
    @Test
    public void addOneProductTenTimesTest(){
        ProductPage productPage = new ProductPage(driver);
        int productQty = productPage.goTo(productUrl).addToCartWithQty(10).viewCart().getProductQty();

        assertEquals(10, productQty,
                "Quantity of the product is not what expected. Expected: 10, but was " + productQty);
    }

}
