package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.steps.GmailPageSteps;
import kz.epam.atm.gmailtestPF.steps.LoginPageSteps;
import kz.epam.atm.gmailtestPF.utils.ScreenshotExecutor;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

@Listeners(ScreenshotExecutor.class)
public abstract class BaseTest {

    protected GmailPageSteps gmailPageSteps;
    private LoginPageSteps loginPageSteps;
    protected static Logger log = Logger.getRootLogger();

    @BeforeSuite
    @Parameters("browser")
    public void setUp(String browserName){
        FactoryDriver.setBrowserName(browserName);
        log.info("===================================================start logging new tests===================================================");
        loginPageSteps = new LoginPageSteps();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        FactoryDriver.closeDriver();
    }

    protected void login(User user){
        gmailPageSteps = loginPageSteps.openLoginPage(PropertyProvider.getProperty("url"))
                .authorization(user);
        Assert.assertTrue(loginPageSteps.isLogoutButtonPresent(), LOGIN_FAIL_ERR_MSG);
        log.info("login is successful");
        log.info("on the Main page");
    }

    protected void logout(){
        loginPageSteps.logout();
        Assert.assertFalse(loginPageSteps.isLogoutButtonPresent(), LOGOUT_FAIL_ERR_MSG);
        log.info("logout is successful");
    }

}
