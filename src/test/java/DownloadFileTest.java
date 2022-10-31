import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class DownloadFileTest {

    private WebDriver driver;
    private final String PAGE_URL = "http://the-internet.herokuapp.com/download";
    private final By downloadFile = By.xpath("//a[@href='download/sample.png']");

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
    public void checkFileDownload() throws InterruptedException {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.setProperty("user.dir", "C:\\Users\\111\\Downloads"));

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(downloadFile).click();

        Thread.sleep(3000);

        File folder = new File(System.getProperty("user.dir"));

        //List the files on that folder
        File[] listOfFiles = folder.listFiles();

        boolean found = false;
        File f = null;

        //Look for the file in the files
        // You should write smart REGEX according to the filename
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("File " + listOfFile.getName());
                if (fileName.matches("sample.png" )) {
                    f = new File(fileName);
                    found = true;
                }

            }
        }

        Assert.assertTrue(found, "Downloaded document is not found");
    }

    /*@AfterClass
    public void closeBrowser(){
        driver.quit();}*/

}
