import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
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
        //System.out.println(actualquantity);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
