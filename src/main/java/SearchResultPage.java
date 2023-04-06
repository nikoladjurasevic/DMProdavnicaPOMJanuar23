import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SearchResultPage extends BasePage{


    @FindBy(xpath = "//button[@data-dmid='search-tab-product']")
    WebElement productCategory;

    @FindBy(xpath = "//div[@data-dmid='product-tiles']")
    WebElement listOfProductTiles;

    public SearchResultPage(ChromeDriver driver) {
        super(driver);
        print("SearchResultPage");
    }

    public boolean isPageLoaded() {
        wait(10);
        return isElementPresent(productCategory);
    }

    public List<WebElement> getAllProducts() {
        return listOfProductTiles.findElements(By.xpath(".//div[@data-dmid='product-tile-container']"));
    }

    public boolean verifyFirstItemContainsText(String text) {
        List<WebElement> itemList = getAllProducts();
        WebElement firstItem = itemList.get(0);
        String itemName = firstItem.findElement(By.xpath(".//div[@data-dmid='product-description']/a/span")).getText().toLowerCase();
        return itemName.contains(text);
    }

    public void selectItemFromList(String text) {
        List<WebElement> itemList = getAllProducts();
        for (WebElement item : itemList) {
            String itemName = item.findElement(By.xpath(".//div[@data-dmid='product-description']/a/span")).getText().toLowerCase();
            if(itemName.contains(text)) {
                scrollIntoView(item);
                WebElement cartIcon = item.findElement(By.xpath(".//*[@data-dmid = 'add-direct-to-cart-button']"));
                hoverOverElement(cartIcon);
                cartIcon.click();
                break;
            }
        }
    }

}
