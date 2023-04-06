import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FindBy (xpath = "//input[@data-dmid = 'search-input']")
    WebElement searchField;

    @FindBy (xpath = "//button[@data-dmid = 'search-submit']")
    WebElement searchButton;

    @FindBy (xpath = "//div[@data-dmid = 'desktop-suggestions-layer']")
    WebElement searchSuggestionDropdown;

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

    public void enterTextIntoSearchField(String text) {
        searchField.click();
        searchField.sendKeys(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void enterTextAndSearch(String text) {
        enterTextIntoSearchField(text);
        clickSearchButton();
    }

    public void selectFromDropdown(String text) {
        waitForElement(searchSuggestionDropdown);
        List<WebElement> listOfSearchSuggestions = searchSuggestionDropdown.findElements(By.xpath(".//span[@data-dmid = 'search-suggestion-highlighted-text']"));
        for (WebElement suggestion : listOfSearchSuggestions) {
            String suggestionText = suggestion.getText();
            if(suggestionText.equals(text)) {
                hoverOverElement(suggestion);
                suggestion.click();
                break;
            }
        }

    }

    public void hoverOverElement(WebElement element) {
        //Creating object of an Actions class
        Actions action = new Actions(driver);
        //Performing the mouse hover action on the target element.
        action.moveToElement(element).perform();
    }



    public String getNumberOfItemsInCart() {
        print("getNumberOfItemsInCart");
        assert isElementPresent(cartNumberOfItems) : "Cart Number is NOT present";
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

    public void wait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);

    }


}
