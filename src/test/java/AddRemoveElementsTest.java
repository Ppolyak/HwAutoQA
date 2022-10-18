import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddRemoveElementsTest {
    private WebDriver driver;
    private final String pageUrl = "http://the-internet.herokuapp.com/add_remove_elements/";
    private final By addElement = By.xpath("//button[text()='Add Element']");
    private final By removeElement = By.xpath("//button[text()='Delete']");
    private final int quantityElementsAfterDeleteOne = 1;



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
    public void addElementTest(){
        driver.get(pageUrl);
        driver.findElement(addElement).click();
        driver.findElement(addElement).click();
        driver.findElement(removeElement).click();
        Boolean exist = driver.findElement(removeElement).isDisplayed();
        int actualquantity = driver.findElements(removeElement).size();
        Assert.assertEquals(actualquantity,quantityElementsAfterDeleteOne);

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
