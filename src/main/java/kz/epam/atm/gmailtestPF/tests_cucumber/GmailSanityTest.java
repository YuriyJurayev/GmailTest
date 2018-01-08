package kz.epam.atm.gmailtestPF.tests_cucumber;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.steps.GmailPageSteps;
import kz.epam.atm.gmailtestPF.steps.LoginPageSteps;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(strict = true, plugin = {"pretty"}, tags = {"@functional_test", "~@smoke_test"}, features = "src/main/resources/features", glue = {
        "kz.epam.atm.gmailtestPF.step_definitions" },snippets = SnippetType.UNDERSCORE/*monochrome = true*/)
public class GmailSanityTest extends AbstractTestNGCucumberTests{

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
