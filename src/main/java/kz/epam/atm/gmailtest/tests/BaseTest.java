package kz.epam.atm.gmailtest.tests;

import kz.epam.atm.gmailtest.property.GlobalConstants;
import kz.epam.atm.gmailtest.property.PropertyProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        this.driver = null;
    }
    public boolean isElementPresent(){
        return driver.findElements(By.linkText("Выйти")).size() > 0;
    }
}
