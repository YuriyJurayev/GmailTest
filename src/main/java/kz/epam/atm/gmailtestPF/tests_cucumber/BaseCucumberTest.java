package kz.epam.atm.gmailtestPF.tests_cucumber;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.LOGIN_FAIL_ERR_MSG;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.LOGOUT_FAIL_ERR_MSG;

abstract class BaseCucumberTest extends AbstractTestNGCucumberTests {

    private LoginPage loginPage;

    @BeforeSuite
    @Parameters("browser")
    public void setUp(String browserName){
        FactoryDriver.setBrowserName(browserName);
    }

    @BeforeSuite(dependsOnMethods = "setUp")
    public void log_in(){
        loginPage = new LoginPage();
        if(!FactoryDriver.getInstance().getCurrentUrl().matches("^(https://mail\\.google\\.com).*")) {
            FactoryDriver.getInstance().get(PropertyProvider.getProperty("url"));
            loginPage.login(new User(PropertyProvider.getProperty("username"), PropertyProvider.getProperty("password")));
            Assert.assertTrue(DOMElementPresence.isElementPresent(loginPage.getLogoutButton()), LOGIN_FAIL_ERR_MSG);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        FactoryDriver.closeDriver();
    }

    @AfterSuite()
    public void logout() {
        if(FactoryDriver.getInstance().getCurrentUrl().matches("^(https://mail\\.google\\.com).*")) {
            loginPage.logout();
            Assert.assertFalse(DOMElementPresence.isElementPresent(loginPage.getLogoutButton()), LOGOUT_FAIL_ERR_MSG);
        }
    }
}
