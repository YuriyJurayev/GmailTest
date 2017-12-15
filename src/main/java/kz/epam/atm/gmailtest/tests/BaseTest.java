package kz.epam.atm.gmailtest.tests;

import kz.epam.atm.gmailtest.property.GlobalConstants;
import kz.epam.atm.gmailtest.property.PropertyProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;

    @BeforeClass
    public void initData() {
        PropertyProvider.readProperties(GlobalConstants.CONFIG_PROPERTIES_PATH);
    }

    @BeforeMethod
    public void setUp(){
        getCurrentDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        this.driver = null;
    }
    public boolean isElementPresent(){
        return driver.findElements(By.linkText("Выйти")).size() > 0;
    }

    public void getCurrentDriver(){
        String browser = PropertyProvider.getProperty("browser");
        switch (browser){
            case "chrome":
                driver = createChromeDriver();
                break;
            case "ie":
                driver = createIEDriver();
                break;
            case "firefox":
                driver = createIEDriver();
                break;
            default:
                driver = createFirefoxDriver();
                break;
        }
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public WebDriver createChromeDriver(){
        System.setProperty(PropertyProvider.getProperty("chrome_driver"),PropertyProvider.getProperty("chrome_driver_exe") );
        return new ChromeDriver();
    }
    public WebDriver createFirefoxDriver(){
        System.setProperty(PropertyProvider.getProperty("firefox_driver"),PropertyProvider.getProperty("firefox_driver_exe") );
        return new FirefoxDriver();
    }

    public WebDriver createIEDriver(){
        System.setProperty(PropertyProvider.getProperty("chrome_driver"),PropertyProvider.getProperty("chrome_driver_exe") );
        return new InternetExplorerDriver();
    }
}
