import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContextMenu {

    private WebDriver driver;

    private final String PAGE_URL = "http://the-internet.herokuapp.com/context_menu";
    @FindBy (xpath = "//div[@id='hot-spot']")
    private WebElement clickElement;

    private final By clickHere = By.xpath("//div[@id='hot-spot']");



    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @Test
    public void contextClickTest(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get(PAGE_URL);
        Actions actions = new Actions(driver);
        driver.findElement(By.xpath("//div[@id='hot-spot']"));
        actions.moveToElement(driver.findElement(By.xpath("//div[@id='hot-spot']"))).contextClick().perform();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        String expectedAlertText = "You selected a context menu";
        Assert.assertEquals(alertText,expectedAlertText);
    }



    @AfterClass
    public void closeBrowser(){
        driver.quit();}

}
