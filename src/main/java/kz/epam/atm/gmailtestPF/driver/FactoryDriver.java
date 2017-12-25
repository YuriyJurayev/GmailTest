package kz.epam.atm.gmailtestPF.driver;

import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FactoryDriver {

    private static final String FIREFOX_DRIVER = "firefox_driver";
    private static final String FIREFOX_DRIVER_EXE = "firefox_driver_exe";
    private static final String CHROME = "chrome";
    private static final String CHROME_DRIVER = "chrome_driver";
    private static final String CHROME_DRIVER_EXE = "chrome_driver_exe";
    private static final int PAGE_LOAD_TIMEOUT = 15;
    private static final int IMPLICIT_WAIT_TIMEOUT = 10;
    private static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            getCurrentDriver();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

    private static void getCurrentDriver(){
        String browser = PropertyProvider.getProperty("browser");
        DesiredCapabilities capabilities;
        switch (browser){
            case CHROME:
                driver = createChromeDriver();
                //capabilities = DesiredCapabilities.chrome();
                break;
            default:
                driver = createFirefoxDriver();
                //capabilities = DesiredCapabilities.firefox();
                break;
        }
        /*try {
            driver = new RemoteWebDriver(new URL("http://10.12.13.36:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private static WebDriver createChromeDriver(){
        System.setProperty(PropertyProvider.getProperty(CHROME_DRIVER), PropertyProvider.getProperty(CHROME_DRIVER_EXE) );
        return new ChromeDriver();
    }
    private static WebDriver createFirefoxDriver(){
        System.setProperty(PropertyProvider.getProperty(FIREFOX_DRIVER), PropertyProvider.getProperty(FIREFOX_DRIVER_EXE) );
        return new FirefoxDriver();
    }

}
