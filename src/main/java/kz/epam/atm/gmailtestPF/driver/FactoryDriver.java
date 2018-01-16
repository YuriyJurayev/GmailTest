package kz.epam.atm.gmailtestPF.driver;

import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

public class FactoryDriver {

    private static WebDriver driver;
    private static String browserName;
    private static Logger log = LogManager.getRootLogger();

    public static void setBrowserName(String browserName) {
        FactoryDriver.browserName = browserName;
    }
    public static WebDriver getInstance() {
        if (driver == null) {
            getCurrentDriver();
        }
        return driver;
    }
    public static void closeDriver() {
        log.info("try to close driver");
        driver.quit();
        driver = null;
        log.info("driver is closed");
    }

    private static void getCurrentDriver(){
        BrowserTypes browser = BrowserTypes.valueOf(browserName);
        log.info("try to create driver");
        switch (browser){
            case CHROME:
                driver = new CustomWebDriver(createChromeDriver());
                log.info("chrome driver is created");
                break;
            default:
                driver = new CustomWebDriver(createFirefoxDriver());
                log.info("firefox driver is created");
                break;
        }
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private static ChromeDriver createChromeDriver(){
        System.setProperty(PropertyProvider.getProperty(CHROME_DRIVER), PropertyProvider.getProperty(CHROME_DRIVER_EXE) );
        return new ChromeDriver();
    }
    private static FirefoxDriver createFirefoxDriver(){
        System.setProperty(PropertyProvider.getProperty(FIREFOX_DRIVER), PropertyProvider.getProperty(FIREFOX_DRIVER_EXE) );
        return new FirefoxDriver();
    }

}
