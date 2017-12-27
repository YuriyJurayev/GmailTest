package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.steps.GmailPageSteps;
import kz.epam.atm.gmailtestPF.steps.LoginPageSteps;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import kz.epam.atm.gmailtestPF.utils.ScreenshotExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

@Listeners(ScreenshotExecutor.class)
public class BaseTest {

    protected WebDriver driver;
    protected GmailPage gmailPage;
    protected GmailPageSteps gmailPageSteps;
    protected LoginPage loginPage;
    private LoginPageSteps loginPageSteps;
    private FactoryDriver factoryDriver;


    @BeforeClass
    @Parameters("browser")
    public void setUp(String browserName){
        factoryDriver = new FactoryDriver();
        driver = factoryDriver.getDriverInstance(browserName);
        gmailPage = new GmailPage(driver);
        loginPage = new LoginPage(driver);
        loginPageSteps = new LoginPageSteps(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        factoryDriver.closeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void login(String login, String password){
        gmailPageSteps = loginPageSteps.openLoginPage(PropertyProvider.getProperty("url"))
                .authorization(new User(login, password));
        Assert.assertTrue(DOMElementPresence.isElementPresent(loginPage.getLogoutButton()), LOGIN_FAIL_ERR_MSG);
    }

    protected void logout(){
        loginPageSteps.logout();
        Assert.assertFalse(DOMElementPresence.isElementPresent(loginPage.getLogoutButton()), LOGOUT_FAIL_ERR_MSG);
    }
    protected void verifyDraftMailExistence(Email email){
        gmailPageSteps.navigateToDraftFolder();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, EXPLICIT_WAIT_TIMEOUT, gmailPage.getDrafMailLabel());
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),DRAFT_EMAIL_ABSENCE_ERR_MSG);
        Assert.assertEquals(gmailPageSteps.getFirstEmailRecipientsFieldText(), email.getRecipients(),INCORRECT_RECIPIENT_ERR_MSG);
        Assert.assertEquals(gmailPageSteps.getFirstEmailSubjectFieldText(), gmailPageSteps.getSubjectContent(), INCORRECT_SUBJECT_ERR_MSG);
        Assert.assertEquals(gmailPageSteps.getFirstEmailBodyFieldText(), email.getBody(),INCORRECT_BODY_ERR_MSG);
    }

    protected void verifyDraftMailAbsence(){
        gmailPageSteps.navigateToDraftFolder();
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getEmptyEmailListSign()),DRAFT_EMAIL_PRESENCE_ERR_MSG);
    }
    protected void verifySentMailExistence(){
        gmailPageSteps.navigateToSentFolder();
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),EMPTY_SENT_FOLDER_ERR_MSG);
    }
}
