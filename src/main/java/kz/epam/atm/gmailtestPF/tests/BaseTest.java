package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.steps.GmailPageSteps;
import kz.epam.atm.gmailtestPF.steps.LoginPageSteps;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ScreenshotExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

//@Listeners(ScreenshotExecutor.class)
public class BaseTest {

    protected WebDriver driver;
    protected GmailPage gmailPage;
    protected GmailPageSteps gmailPageSteps;
    protected LoginPage loginPage;
    private LoginPageSteps loginPageSteps;

    @BeforeSuite
    @Parameters("browser")
    public void setUp(String browserName){
        FactoryDriver.setBrowserName(browserName);
        driver = FactoryDriver.getInstance();
        gmailPage = new GmailPage();
        loginPage = new LoginPage();
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
    }

    protected void logout(){
        loginPageSteps.logout();
        Assert.assertFalse(loginPageSteps.isLogoutButtonPresent(), LOGOUT_FAIL_ERR_MSG);
    }

}
