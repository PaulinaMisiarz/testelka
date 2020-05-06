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

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest {
    WebDriver driver;

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

    @Test
    public void addToCartFromProductPageTest(){
    String productUrl = "https://fakestore.testelka.pl/product/egipt-el-gouna/";
    String productId = "386";
    ProductPage productPage = new ProductPage(driver);
    int productAmout = productPage.goTo(productUrl).addToCart().viewCart().getProductAmount(productId);
    assertTrue(productAmout==1,
                "Remove button was not found for a product with" + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }

}
