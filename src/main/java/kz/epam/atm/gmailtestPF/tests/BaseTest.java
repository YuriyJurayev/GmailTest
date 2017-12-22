package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.property.GlobalConstants;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.utils.ScreenshotExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(ScreenshotExecutor.class)
public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void initData() {
        PropertyProvider.readProperties(GlobalConstants.CONFIG_PROPERTIES_PATH);
    }

    @BeforeClass
    public void setUp(){
        driver = FactoryDriver.getInstance();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        FactoryDriver.closeDriver();
    }
}
