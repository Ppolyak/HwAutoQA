import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InputsTest {
    private WebDriver driver;
    private final String pageUrl = "http://the-internet.herokuapp.com/inputs";
    private final By inputs = By.tagName("input");
    private final String upInput = "1";
    private final String downInput = "-1";
    private final String putNumber = "222";
    private final String putText = "Some text";
    //possible to input "e" symbol )

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
    public void checkUpInputTest(){
        driver.get(pageUrl);
        driver.findElement(inputs).sendKeys(Keys.ARROW_UP);
        String inputValue = driver.findElement(inputs).getAttribute("value");
        System.out.println(inputValue);
        Assert.assertEquals(upInput,inputValue);
    }

    @Test
    public void checkDownInputTest(){
        driver.get(pageUrl);
        driver.findElement(inputs).sendKeys(Keys.ARROW_DOWN);
        String inputValue = driver.findElement(inputs).getAttribute("value");
        System.out.println(inputValue);
        Assert.assertEquals(downInput,inputValue);
    }

    @Test
    public void checkNotNumbetInputTest(){
        driver.get(pageUrl);
        driver.findElement(inputs).sendKeys(putText);
        String inputValue = driver.findElement(inputs).getAttribute("value");
        Assert.assertEquals(putText,inputValue);
    }

    @Test
    public void checkNumberInputTest(){
        driver.get(pageUrl);
        driver.findElement(inputs).sendKeys(putNumber);
        String inputValue = driver.findElement(inputs).getAttribute("value");
        Assert.assertEquals(putNumber,inputValue);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();}
}
