import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

    //webelementi
    @FindBy(xpath = "//h2[text() = 'Organski proizvodi']/parent::div/parent::div/parent::div//div[@class = 'slick-track']")
    WebElement organskiProizvodiLista;

    @FindBy(xpath = "//div[@id='usercentrics-root']")
    WebElement cookieModal;


    //konstruktor
    public HomePage(ChromeDriver driver) {
        super(driver);
        driver.get("https://www.dm.rs/");
        print("HomePage");
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

    public void acceptCookies() {
        print("acceptCookies");
//        waitForElement(cookieModal);
        WebElement consent = driver.findElement(By.xpath("//*[@data-testid = 'uc-accept-all-button']"));
        WebElement ele = (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].shadowRoot",consent);
        ele.click();
//        shadowContent.findElement(By.xpath("//*[@data-testid = 'uc-accept-all-button']")).click();
    }

}
