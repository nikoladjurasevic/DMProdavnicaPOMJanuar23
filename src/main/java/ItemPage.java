import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends BasePage {

    //webelementi
    @FindBy(xpath = "//button[@data-dmid = 'add-to-cart-button']")
    WebElement addToCartButton;

    //konstruktor
    public ItemPage(ChromeDriver driver) {
        super(driver);
        print("ItemPage");
    }

    //methods
    public void addToCartButton() {
        waitForElement(addToCartButton);
        scrollIntoView(addToCartButton);
        addToCartButton.click();
    }

}
