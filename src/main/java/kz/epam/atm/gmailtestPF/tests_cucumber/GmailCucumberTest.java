package kz.epam.atm.gmailtestPF.tests_cucumber;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.steps.GmailPageSteps;
import kz.epam.atm.gmailtestPF.steps.LoginPageSteps;
import org.testng.Assert;
import org.testng.annotations.*;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.LOGIN_FAIL_ERR_MSG;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.LOGOUT_FAIL_ERR_MSG;

@CucumberOptions(strict = true, plugin = {"pretty"}, /*tags = {"@authorization"},*/ features = "src/main/resources/features", glue = {
        "kz.epam.atm.gmailtestPF.step_definitions" },snippets = SnippetType.UNDERSCORE/*monochrome = true*/)
public class GmailCucumberTest extends AbstractTestNGCucumberTests{

    protected GmailPageSteps gmailPageSteps;
    private LoginPageSteps loginPageSteps;

    @BeforeSuite
    //@Parameters("browser")
    public void setUp(){
        FactoryDriver.setBrowserName("FIREFOX");
        loginPageSteps = new LoginPageSteps();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        FactoryDriver.closeDriver();
    }

    /*@BeforeClass
    protected void login(User user){
        gmailPageSteps = loginPageSteps.openLoginPage(PropertyProvider.getProperty("url"))
                .authorization(user);
        Assert.assertTrue(loginPageSteps.isLogoutButtonPresent(), LOGIN_FAIL_ERR_MSG);
    }
    @AfterClass
    protected void logout(){
        loginPageSteps.logout();
        Assert.assertFalse(loginPageSteps.isLogoutButtonPresent(), LOGOUT_FAIL_ERR_MSG);
    }*/


}
