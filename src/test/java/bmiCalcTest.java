import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class bmiCalcTest {
    private WebDriver driver;
    private final String testingPageUrl = "https://healthunify.com/bmicalculator/";
    private final String weightAlertMessage = "Enter the value for weight";
    private final By weightField =  By.xpath("//input[@name='wg']");
    private final String weightValue = "110";
    private String cmsFieldCheck;
    private final String categoryNormal = "Your category is Normal";
    private final By weightTypeSelect =  By.xpath("//select[@name='opt1']");
    private final By weightTypeSelectKilograms = By.xpath("//option[@value='kilograms']");
    private final By weightTypeSelectPounds = By.xpath("//option[@value='pounds']");
    private final By cmsField = By.xpath("//input[@name='ht']");
    private final By calculateButton = By.xpath("//input[@name='cc']");
    private final By categoryResult = By.xpath("//input[@name='desc']");
    private final By heightFirstValue =  By.xpath("//select[@name='opt2']");
    private final By heightFirstValue1 =  By.xpath("//option[@value='1']");
    private final By heightFirstValue2 =  By.xpath("//option[@value='2']");
    private final By heightFirstValue3 =  By.xpath("//option[@value='3']");
    private final By heightFirstValue4 =  By.xpath("//option[@value='4']");
    private final By heightFirstValue5 =  By.xpath("//option[@value='5']");
    private final By heightFirstValue6 =  By.xpath("//option[@value='6']");
    private final By heightFirstValue7 =  By.xpath("//option[@value='7']");
    private final By heightSecondValue =  By.xpath("//select[@name='opt3']");
    private final By heightSecondValue1 =  By.xpath("//*[contains(text(), '1″')]");
    private final By heightSecondValue2 =  By.xpath("//*[contains(text(), '2″')]");
    private final By heightSecondValue3 =  By.xpath("//*[contains(text(), '3″')]");
    private final By heightSecondValue4 =  By.xpath("//*[contains(text(), '4″')]");
    private final By heightSecondValue5 =  By.xpath("//*[contains(text(), '5″')]");
    private final By heightSecondValue6 =  By.xpath("//*[contains(text(), '6″')]");
    private final By heightSecondValue7 =  By.xpath("//*[contains(text(), '7″')]");
    private final By heightSecondValue8 =  By.xpath("//*[contains(text(), '8″')]");
    private final By heightSecondValue9 =  By.xpath("//*[contains(text(), '9″')]");
    private final By heightSecondValue10 =  By.xpath("//*[contains(text(), '10″')]");
    private final By heightSecondValue11 =  By.xpath("//*[contains(text(), '11″')]");

    public bmiCalcTest(String cmsFieldCheck) {
        this.cmsFieldCheck = cmsFieldCheck;
    }

    @BeforeClass
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void normalCategoryTest(){
        driver.get(testingPageUrl);
        driver.findElement(weightField).sendKeys(weightValue);
        driver.findElement(weightTypeSelect).click();
        driver.findElement(weightTypeSelectKilograms);
        driver.findElement(heightFirstValue).click();
        driver.findElement(heightFirstValue7).click();
        driver.findElement(heightSecondValue).click();
        driver.findElement(heightSecondValue11).click();
        driver.findElement(calculateButton).click();
        String abs = String.valueOf(driver.findElement(categoryResult).getAttribute("value"));
        Assert.assertEquals(categoryNormal,abs);
    }

    @Test
    public void growthPoundsInCmsConvertTest(){
        Actions actions = new Actions(driver);
        driver.get(testingPageUrl);
        driver.findElement(weightField).sendKeys(weightValue);
        driver.findElement(weightTypeSelect).click();
        driver.findElement(weightTypeSelectKilograms);
        driver.findElement(heightFirstValue).click();
        driver.findElement(heightFirstValue7).click();
        driver.findElement(heightSecondValue).click();
        driver.findElement(heightSecondValue11).click();
        cmsFieldCheck = "241";
        String abs = String.valueOf(driver.findElement(cmsField).getAttribute("value"));
        Assert.assertEquals(cmsFieldCheck, abs);
    }

    @Test
    public void ThirdTest(){
        driver.get(testingPageUrl);
        driver.findElement(calculateButton).click();
        String alertWindowText = driver.switchTo().alert().getText();
        Assert.assertEquals(weightAlertMessage, alertWindowText);
    }


    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}

