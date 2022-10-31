import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FrameTest {

    private WebDriver driver;
    private final String PAGE_URL = "http://the-internet.herokuapp.com/frames";
    private final By iFrameLink = By.xpath("//a[@href='/iframe']");
    private final By textToCheck = By.xpath("html/body[@id='tinymce']");
    private final By frame = By.xpath("//iframe[@id='mce_0_ifr']");
    private String frameId = "mce_0_ifr";
    private String expectedText = "Your content goes here.";


    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(PAGE_URL);
    }

    @Test
    public void checkTextTest(){
        driver.findElement(iFrameLink).click();
        driver.switchTo().frame(frameId);
        driver.findElement(textToCheck).click();
        String actualText = driver.findElement(textToCheck).getText();
        Assert.assertEquals(actualText,expectedText);
    }


    /*@AfterClass
    public void closeBrowser(){
        driver.quit();}*/

}
