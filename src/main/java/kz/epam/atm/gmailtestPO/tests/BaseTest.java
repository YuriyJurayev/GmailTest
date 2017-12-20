package kz.epam.atm.gmailtestPO.tests;

import kz.epam.atm.gmailtestPO.FactoryDriver.FactoryDriver;
import kz.epam.atm.gmailtestPO.property.GlobalConstants;
import kz.epam.atm.gmailtestPO.property.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    WebDriver driver;

    @BeforeClass
    public void initData() {
        PropertyProvider.readProperties(GlobalConstants.CONFIG_PROPERTIES_PATH);
    }

    @BeforeMethod
    public void setUp(){
        driver = FactoryDriver.getCurrentDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
