import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SortabelDataTablesTest {
    private WebDriver driver;
    private final String pageUrl = "http://the-internet.herokuapp.com/tables";
    private final By firstCell = By.xpath("//table//tr[2]//td[5]");
    private final By secondCell = By.xpath("//table//tr[4]//td[3]");
    private final By thirdCell = By.xpath("//table//tr[3]//td[4]");
    private final String firstCellText = "http://www.frank.com";
    private final String secondCellText = "tconway@earthlink.net";
    private final String thirdCellText = "$100.00";

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
    public void checkFirstCellTest(){
        driver.get(pageUrl);
        String cellText = driver.findElement(firstCell).getText();
        Assert.assertEquals(firstCellText,cellText);
    }

    @Test
    public void checkSecondCellTest(){
        driver.get(pageUrl);
        String cellText = driver.findElement(secondCell).getText();
        Assert.assertEquals(secondCellText,cellText);
    }

    @Test
    public void checkThirdCellTest(){
        driver.get(pageUrl);
        String cellText = driver.findElement(thirdCell).getText();
        Assert.assertEquals(thirdCellText,cellText);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();}
}
