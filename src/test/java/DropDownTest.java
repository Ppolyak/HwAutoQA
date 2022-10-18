import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

public class DropDownTest {
    private WebDriver driver;
    private final String pageUrl = "http://the-internet.herokuapp.com/dropdown";
    private final By dropDownList = By.id(("dropdown"));
    private final String dropDownItems = "[Please select an option, Option 1, Option 2]";

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
    public void checkAllDropDownElementsTest(){
        driver.get(pageUrl);
        Select select = new Select(driver.findElement(dropDownList));
        ArrayList<WebElement> webElements = (ArrayList<WebElement>) select.getOptions();
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < webElements.size(); i++){
            items.add(webElements.get(i).getText());
        }
        Assert.assertEquals(items.toString(),dropDownItems);
    }

    @Test
    public void selectFirstElementAndCheckTest(){
        driver.get(pageUrl);
        Select select = new Select(driver.findElement(dropDownList));
        /*select.selectByVisibleText("Option 1");*/
        select.selectByIndex(1);
        boolean isFirstSelected = select.getOptions().get(1).isSelected();
        Assert.assertTrue(isFirstSelected,"First option selected");
    }

    @Test
    public void selectSecondElementAndCheckTest(){
        driver.get(pageUrl);
        Select select = new Select(driver.findElement(dropDownList));
        /*select.selectByVisibleText("Option 1");*/
        select.selectByIndex(2);
        boolean isSecondSelected = select.getOptions().get(2).isSelected();
        Assert.assertTrue(isSecondSelected,"First option selected");
    }


    @AfterClass
    public void closeBrowser(){
        driver.quit();}

}
