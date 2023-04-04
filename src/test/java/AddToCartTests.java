import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AddToCartTests extends BaseTest{

    @Test
    public void addPalentaToCart() {
        ChromeDriver driver = openChromeDriver();
        try {
            HomePage homePage = new HomePage(driver);
//            homePage.acceptCookies();
            homePage.clickOnItemFromOrganskiProizvodi("Palenta");

            ItemPage itemPage = new ItemPage(driver);
            itemPage.addToCartButton();

            String actualNumberOfItemsInCart = itemPage.getNumberOfItemsInCart();
            assert actualNumberOfItemsInCart.equals("1") : "Wrong number of items.Expected" +
                    " 1. Actual " + actualNumberOfItemsInCart;



        }finally {
//            driver.quit();
        }
    }

}
