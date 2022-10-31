import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FileUpload {

    private WebDriver driver;
    private final String PAGE_URL = "http://the-internet.herokuapp.com/upload";
    private final By fileUpload = By.xpath("//input[@id='file-upload']");
    private final By uploadButton = By.xpath("//input[@id='file-submit']");
    private final By fileUploadedText = By.xpath("//div[@class='example']//descendant::h3");
    private final By fileUploadedNameText = By.xpath("//div[@id='uploaded-files']");
    private String fileName = "8R1GkdSl_2g.jpg";
    private String fileUploadedExpectedText = "File Uploaded!";

    private final String filePath = "C:\\Users\\111\\Desktop\\8R1GkdSl_2g.jpg";

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
    public void checkFileUploadTest(){
        driver.findElement(fileUpload).sendKeys(filePath);
        driver.findElement(uploadButton).click();
        String actualFileUploadedText = driver.findElement(fileUploadedText).getText();
        Assert.assertEquals(actualFileUploadedText,fileUploadedExpectedText);
    }

    @Test
    public void checkFileUploadNameTest(){
        driver.findElement(fileUpload).sendKeys(filePath);
        driver.findElement(uploadButton).click();
        String actualFileUploadedText = driver.findElement(fileUploadedNameText).getText();
        Assert.assertEquals(actualFileUploadedText,fileName);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();}

}
