import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    //mozete imate i atribut ChromeDriver
    ChromeDriver driver;

    //webelements
    @FindBy (xpath = "//a[@data-dmid = 'logo-link']")
    WebElement upperDMLogo;

    @FindBy (xpath = "//a[@data-dmid = 'cart-link']")
    WebElement shoppingCart;


    @FindBy (xpath = "//span[@data-dmid = 'cart-summary-items']")
    WebElement cartNumberOfItems;


    //Konstruktor
    public BasePage() {}

    public BasePage(ChromeDriver driver) {
        //ovde setujete da drajver iz testa bude i u klasi
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //metode nad webelementima

    public void clickShoppingCart() {
        print("clickShoppingCart");
        shoppingCart.click();
    }

    public String getNumberOfItemsInCart() {
        print("getNumberOfItemsInCart");
        scrollIntoView(cartNumberOfItems);
        waitForElement(cartNumberOfItems);
        return cartNumberOfItems.getText();
    }



    //pomocne metode
    //ovo sluzi samo za printanje texta u konzoli
    public static void print(String s) {
        System.out.println(s);
    }



    public boolean verifyURL(String expectedUrl) {
        print("verifyURL ( " + expectedUrl + " )");
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals(expectedUrl);
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        }catch (Exception e) {
            print(e.getMessage());
        }
    }

    public boolean isElementPresent(WebElement element) {
        try{
            print("isElementPresent()");
            boolean isPresent = element.isDisplayed();
            return isPresent;
        }catch (Exception e) {
            return false;
        }
    }

    //ovo cemo koristiti za elemente stranice gde treba da se skroluje do njih
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);" ,element);
    }

    public void waitForElement(WebElement element) {
        //ovde najcesce stavljam wait zato sto ga koristim da sacekam da se webelementi pojave a oni su u Page klasama
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}
