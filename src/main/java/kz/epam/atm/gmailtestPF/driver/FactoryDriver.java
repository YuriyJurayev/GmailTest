package kz.epam.atm.gmailtestPF.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FactoryDriver {

    private WebDriver driver;

    public void closeDriver() {
        driver.quit();
    }

    public WebDriver getDriverInstance(String browserName){
        BrowserTypes browserType = BrowserTypes.valueOf(browserName);
        DesiredCapabilities capabilities;
        switch (browserType){
            case CHROME:
                capabilities = DesiredCapabilities.chrome();
                break;
            default:
                capabilities = DesiredCapabilities.firefox();
                break;
        }
        try {
            driver = new RemoteWebDriver(new URL("http://10.12.13.36:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }


}
