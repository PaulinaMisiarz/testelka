package TestyPOM;

import PageObjects.CategoryPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {
    String productId = "386";
    String productUrl = "https://fakestore.testelka.pl/product/egipt-el-gouna/";
    String categoryUrl = "https://fakestore.testelka.pl/product-category/windsurfing/";
    String[] productPages = {"/egipt-el-gouna/","/wspinaczka-via-ferraty/","/wspinaczka-island-peak/",
            "/fuerteventura-sotavento/", "/grecja-limnos/", "/windsurfing-w-karpathos/",
            "/wyspy-zielonego-przyladka-sal/", "/wakacje-z-yoga-w-kraju-kwitnacej-wisni/",
            "/wczasy-relaksacyjne-z-yoga-w-toskanii/", "/yoga-i-pilates-w-hiszpanii/"};


    @Test
    public void addToCartFromProductPageTest(){
    ProductPage productPage = new ProductPage(driver);
    productPage.footer.closeDemoNotice();
    boolean isProductInCart = productPage.goTo(productUrl).addToCart().viewCart().isProductInCart(productId);

    assertTrue(isProductInCart,
                "Remove button was not found for a product with " + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }

    @Test
    public void addToCartFromCategoryPageTest(){
        CategoryPage categoryPage = new CategoryPage(driver).goTo(categoryUrl);
        categoryPage.footer.closeDemoNotice();
        boolean isProductInCart  = categoryPage.addToCart(productId).viewCart().isProductInCart(productId);

        assertTrue(isProductInCart,
                "Remove button was not found for a product with" + productId + " (Egipt - El Gouna). " +
                        "Was the product added to cart?");
    }

    @Test
    public void addOneProductTenTimesTest(){
        ProductPage productPage = new ProductPage(driver).goTo(productUrl);
        productPage.footer.closeDemoNotice();
        int productQty = productPage.addToCartWithQty(10).viewCart().getProductQty();

        assertEquals(10, productQty,
                "Quantity of the product is not what expected. Expected: 10, but was " + productQty);
    }
    @Test
    public void addTenProductsToCartTest(){
        ProductPage productPage = new ProductPage(driver);

        for (String product: productPages) {
            productPage.goTo("https://fakestore.testelka.pl/product" + product).addToCart();
        }
        int numberOfItems = productPage.header.viewCart().getNumberOfProducts();

        assertEquals(10, numberOfItems,
                "Number of items in the cart is not correct. Expected: 10, but was: " + numberOfItems);
    }

}
