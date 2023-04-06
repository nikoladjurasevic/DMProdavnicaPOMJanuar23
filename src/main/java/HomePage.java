import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage{

    //webelementi
    @FindBy(xpath = "//h2[text() = 'Organski proizvodi']/parent::div/parent::div/parent::div//div[@class = 'slick-track']")
    WebElement organskiProizvodiLista;

    @FindBy(xpath = "//div[@id='usercentrics-root']")
    WebElement cookieModal;

    @FindBy(xpath = "//button[@data-dmid='layer-header-close-button']")
    WebElement closePromotionButton;



    //konstruktor
    public HomePage(ChromeDriver driver) {
        super(driver);
        driver.get("https://www.dm.rs/");
        print("HomePage");
        acceptCookies();
        closePromotion();
    }

    public void acceptCookies() {
        wait(20);
        WebElement root = driver.findElement(By.xpath("//div[@id = 'usercentrics-root']"));
        Object shadowRoot = ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",  root);

        Map<String, Object> shadowRootMap = (Map<String, Object>) shadowRoot;
        String shadowRootKey = (String) shadowRootMap.keySet().toArray()[0];
        String id = (String) shadowRootMap.get(shadowRootKey);
        RemoteWebElement remoteWebElement = new RemoteWebElement();
        remoteWebElement.setParent((RemoteWebDriver) driver);
        remoteWebElement.setId(id);
        WebElement returnObj = remoteWebElement;

        WebElement acceptAllCookiesButton = returnObj.findElement(By.cssSelector("button[data-testid='uc-accept-all-button']"));
        acceptAllCookiesButton.click();
    }

    public void closePromotion() {
        print("closePromotion");
        waitForElement(closePromotionButton);
        closePromotionButton.click();
    }

    //metode nad webelementima

    public void clickOnItemFromOrganskiProizvodi(String itemName) {
        print("clickOnItemFromOrganskiProizvodi ( " + itemName + " )");
        waitForElement(organskiProizvodiLista);
        scrollIntoView(organskiProizvodiLista);
        List<WebElement> itemArray = organskiProizvodiLista.findElements(By.xpath(".//a[@data-dmid = 'dm-link']"));
        for (int i = 0; i< itemArray.size(); i ++) {
            WebElement item = itemArray.get(i);
            String itemTitle = item.getAttribute("title");
            if(itemTitle.equals(itemTitle)) {
                item.click();
                break;
            }
        }


    }

}
