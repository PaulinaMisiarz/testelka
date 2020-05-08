package TestyPOM;

import PageObjects.CategoryPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {
    String productId = "386";
    String productUrl = "https://fakestore.testelka.pl/product/egipt-el-gouna/";
    String categoryUrl = "https://fakestore.testelka.pl/product-category/windsurfing/";


    @Test
    public void addToCartFromProductPageTest(){
    ProductPage productPage = new ProductPage(driver).goTo(productUrl);
    productPage.closeDemoNotice();
    int productAmount = productPage.addToCart().viewCart().getProductAmount(productId);

    assertTrue(productAmount==1,
                "Remove button was not found for a product with" + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }

    @Test
    public void addToCartFromCategoryPageTest(){
        CategoryPage categoryPage = new CategoryPage(driver).goTo(categoryUrl);
        categoryPage.closeDemoNotice();
        int productAmount = categoryPage.addToCart(productId).viewCart().getProductAmount(productId);

        assertTrue(productAmount==1,
                "Remove button was not found for a product with" + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");

    }
    @Test
    public void addOneProductTenTimesTest(){
        ProductPage productPage = new ProductPage(driver).goTo(productUrl);
        productPage.closeDemoNotice();
        int productQty = productPage.addToCartWithQty(10).viewCart().getProductQty();

        assertEquals(10, productQty,
                "Quantity of the product is not what expected. Expected: 10, but was " + productQty);
    }

}
