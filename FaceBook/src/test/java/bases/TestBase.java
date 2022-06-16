package bases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.Helper;
import utilities.propertiesFile;
import java.util.concurrent.TimeUnit;

public class TestBase{
    public static WebDriver driver;
    public static String browser = null;

    @BeforeSuite
    public static void setUp() {
            propertiesFile.readProperitesFile();
            if (browser.equalsIgnoreCase("chrome"))
            {
                System.setProperty("webdriver.chrome.driver", "Resources/Drivers/chromedriver.exe");
                //Create instance of ChromeOptions Class
                driver = new ChromeDriver();
            }

            else if (browser.equalsIgnoreCase("firefox"))
            {
                System.setProperty("webdriver.gecko.driver", "Resources/Drivers/geckodriver.exe");
                FirefoxOptions handlingSSL = new FirefoxOptions();
                handlingSSL.setAcceptInsecureCerts(true);
                //Using the accept insecure cert method with true as parameter to accept the untrusted certificate
                driver = new FirefoxDriver(handlingSSL);
            }

            driver.get("https://www.facebook.com/login/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        }


 // take screenshot when test case fail and add it in the Screenshot folder
 	@AfterMethod
 	public void screenshotOnFailure(ITestResult result)
 	{
 		//if (result.getStatus() == ITestResult.FAILURE)
 		{
 			System.out.println("Failed!");
 			System.out.println("Taking Screenshot....");
 			Helper.captureScreenshot(driver,result.getName());
 		}
 	}

    @AfterSuite
      public void tearDown(){
      driver.quit();
    }

}
