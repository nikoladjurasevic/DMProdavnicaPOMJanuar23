import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SearchAndFilterTests {



    @Test
    public void searchKremaTest() {
        ChromeDriver driver = new ChromeDriver();
        try {
            HomePage homePage = new HomePage(driver);
            homePage.enterTextAndSearch("krema");

            SearchResultPage searchResultPage = new SearchResultPage(driver);
            assert searchResultPage.isPageLoaded() : "User is NOT on Search page";

            assert searchResultPage.verifyFirstItemContainsText("krema") : "Wrong item name. Expected: krema";

        }finally {
            driver.quit();
        }
    }


    @Test
    public void searchByTextAndChooseFromDropdown() {
        ChromeDriver driver = new ChromeDriver();
        try {
            HomePage homePage = new HomePage(driver);
            homePage.enterTextIntoSearchField("krema");
            homePage.selectFromDropdown("krema za telo");

            SearchResultPage searchResultPage = new SearchResultPage(driver);
            assert searchResultPage.isPageLoaded() : "User is NOT on Search page";

            assert searchResultPage.verifyFirstItemContainsText("krema za telo") : "Wrong item name. Expected: krema za telo";

        }finally {
            driver.quit();
        }
    }

    @Test
    public void searchByTextAndSelectFromItemList() {
        ChromeDriver driver = new ChromeDriver();
        try {
            HomePage homePage = new HomePage(driver);
            homePage.enterTextAndSearch("shea butter");

            SearchResultPage searchResultPage = new SearchResultPage(driver);
            assert searchResultPage.isPageLoaded() : "User is NOT on Search page";
            searchResultPage.selectItemFromList("manuka honey");

            String actualNumberOfItemsInCart = searchResultPage.getNumberOfItemsInCart();
            assert actualNumberOfItemsInCart.equals("1") : "Wrong number of items.Expected" +
                    " 1. Actual " + actualNumberOfItemsInCart;
        }finally {
            driver.quit();
        }
    }


}
