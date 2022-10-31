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

import java.sql.Driver;
import java.time.Duration;

public class DynamicControl {

    private WebDriver driver;
    private final String PAGE_URL = "http://the-internet.herokuapp.com/dynamic_controls";
    /*@FindBy (xpath = "//input[@type='checkbox']")
    private WebElement checkBox;*/
    private final By checkBox = By.xpath("//input[@type='checkbox']");
    private final By goneText = By.xpath("//p[@id='message']");
    private final By removeButton = By.xpath("//form[@id='checkbox-example']/button[@type='button']");
    private final By inputField = By.xpath("//input[@type='text']");
    private final By enableButton = By.xpath("//form[@id='input-example']/button[@type='button']");
    private final By itsEnabledText = By.xpath("//p[@id='message']");
    @FindBy (xpath = "//p[@id='message']")
    private WebElement gone;
    @FindBy (xpath = "//form[@id='checkbox-example']/button[@type='button']")
    private WebElement rmButton;
    public void clickContinueButton(){
        rmButton.click();
    }

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
    public void checkboxExistTest(){
        Boolean checkBoxExist = driver.findElement(checkBox).isDisplayed();
        Assert.assertTrue(checkBoxExist);
    }


    @Test
    public void checkGoneTextTest(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.findElement(removeButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(goneText));
        Boolean noCheckbox = driver.findElements(checkBox).isEmpty();
        Assert.assertTrue(noCheckbox);
    }

    @Test
    public void checkInputExistTest(){
        Boolean inputExist = driver.findElement(inputField).isDisplayed();
        Assert.assertTrue(inputExist);
    }

    @Test
    public void checkInputDisableTest(){
        Boolean inputExist = driver.findElement(inputField).isEnabled();
        Assert.assertFalse(inputExist);
    }

    @Test
    public void checkClickOnEnableButtonTest(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.findElement(enableButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(itsEnabledText));
        Boolean inputEnable = driver.findElement(inputField).isEnabled();
        Assert.assertTrue(inputEnable);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();}

}
