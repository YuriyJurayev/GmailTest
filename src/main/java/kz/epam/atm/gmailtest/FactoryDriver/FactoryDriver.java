package kz.epam.atm.gmailtest.FactoryDriver;

import kz.epam.atm.gmailtest.property.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class FactoryDriver {

    private static final String FIREFOX_DRIVER = "firefox_driver";
    private static final String FIREFOX_DRIVER_EXE = "firefox_driver_exe";
    private static final String CHROME_DRIVER = "chrome_driver";
    private static final String CHROME_DRIVER_EXE = "chrome_driver_exe";

    public static WebDriver getCurrentDriver(){
        String browser = PropertyProvider.getProperty("browser");
        WebDriver driver;
        switch (browser){
            case "chrome":
                driver = createChromeDriver();
                break;
            default:
                driver = createFirefoxDriver();
                break;
        }
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver createChromeDriver(){
        System.setProperty(PropertyProvider.getProperty(CHROME_DRIVER),PropertyProvider.getProperty(CHROME_DRIVER_EXE) );
        return new ChromeDriver();
    }
    private static WebDriver createFirefoxDriver(){
        System.setProperty(PropertyProvider.getProperty(FIREFOX_DRIVER),PropertyProvider.getProperty(FIREFOX_DRIVER_EXE) );
        return new FirefoxDriver();
    }

}
