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

import java.util.ArrayList;

public class CheckBoxesTest {

    private WebDriver driver;
    private final String pageUrl = "http://the-internet.herokuapp.com/checkboxes";
    private final By checkBoxSelector = By.cssSelector("[type=checkbox]");
    private final String checkBoxSelectorr = "[type=checkbox]";
    private final int firstCheckBox = 0;
    private final int secondCheckBox = 1;
    private final boolean uncheckedCheckBox = false;
    private final boolean checkedCheckBox = true;
   /* ArrayList<WebElement> checkBoxes = (ArrayList<WebElement>) driver.findElements(checkBoxSelector);*/


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
    public void checkFirstCheckBoxIsUncheckedTest(){
        driver.get(pageUrl);
        ArrayList<WebElement> checkBoxes = (ArrayList<WebElement>) driver.findElements(checkBoxSelector);
        WebElement element = checkBoxes.get(firstCheckBox);
        boolean firstCheckBoxResult = element.isSelected();
        Assert.assertEquals(firstCheckBoxResult,uncheckedCheckBox);
    }

    @Test
    public void selectFirstCheckBoxAndCheckTest(){
        driver.get(pageUrl);
        ArrayList<WebElement> checkBoxes = (ArrayList<WebElement>) driver.findElements(checkBoxSelector);
        WebElement element = checkBoxes.get(firstCheckBox);
        element.click();
        boolean isFirstChecked = checkBoxes.get(firstCheckBox).isSelected();
        Assert.assertEquals(isFirstChecked,checkedCheckBox);
    }

    @Test
    public void checkSecondIsSelectedTest(){
        driver.get(pageUrl);
        ArrayList<WebElement> checkBoxes = (ArrayList<WebElement>) driver.findElements(checkBoxSelector);
        WebElement element = checkBoxes.get(secondCheckBox);
        boolean secondCheckBoxResult = element.isSelected();
        Assert.assertEquals(secondCheckBoxResult,checkedCheckBox);
    }

    @Test
    public void uncheckSecondCheckBoxTest(){
        driver.get(pageUrl);
        ArrayList<WebElement> checkBoxes = (ArrayList<WebElement>) driver.findElements(checkBoxSelector);
        WebElement element = checkBoxes.get(secondCheckBox);
        element.click();
        boolean isSecondCheckBoxChecked = checkBoxes.get(secondCheckBox).isSelected();
        Assert.assertEquals(isSecondCheckBoxChecked,uncheckedCheckBox);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();}

}
