import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TyposTest {
    private WebDriver driver;
    private final String pageUrl = "http://the-internet.herokuapp.com/typos";
    private final By typoText = By.xpath("//*[contains(text(), 'Sometimes you')]");
    private final String rightTextVersion = "Sometimes you'll see a typo, other times you won't.";

    @BeforeClass
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void checkTypoInTextTest(){
        driver.get(pageUrl);
        String textToCheck = driver.findElement(typoText).getText();
        Assert.assertEquals(rightTextVersion,textToCheck);
        /*System.out.println(textToCheck);*/
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();}
}
