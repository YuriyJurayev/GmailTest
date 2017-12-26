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


    //@BeforeSuite
    @BeforeClass
    public void setUp(){
        driver = FactoryDriver.getInstance();
        gmailPage = new GmailPage();
        loginPage = new LoginPage();
        loginPageSteps = new LoginPageSteps();
    }

    @BeforeMethod
    protected void login(){
        gmailPageSteps = loginPageSteps.openLoginPage(PropertyProvider.getProperty("url"))
                .authorization(new User(PropertyProvider.getProperty("first_login"), PropertyProvider.getProperty("first_password")));
        Assert.assertTrue(DOMElementPresence.isElementPresent(loginPage.getLogoutButton()), LOGIN_FAIL_ERR_MSG);
    }

    @AfterMethod
    protected void logout(){
        loginPageSteps.logout();
        Assert.assertFalse(DOMElementPresence.isElementPresent(loginPage.getLogoutButton()), LOGOUT_FAIL_ERR_MSG);
    }
    //@BeforeSuite(alwaysRun = true)
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        FactoryDriver.closeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
    protected void verifyDraftMailExistence(Email email){
        gmailPageSteps.navigateToDraftFolder();
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, gmailPage.getDrafMailLabel());
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),DRAFT_EMAIL_ABSENCE_ERR_MSG); //checked
        Assert.assertEquals(gmailPageSteps.getFirstEmailRecipientsFieldText(), email.getRecipients(),INCORRECT_RECIPIENT_ERR_MSG); ///check locators
        Assert.assertEquals(gmailPageSteps.getFirstEmailSubjectFieldText(), gmailPageSteps.getSubjectBuilder(), INCORRECT_SUBJECT_ERR_MSG);
        Assert.assertEquals(gmailPageSteps.getFirstEmailBodyFieldText(), email.getBody(),INCORRECT_BODY_ERR_MSG);
    }

    protected void verifyDraftMailAbsence(){
        gmailPageSteps.navigateToDraftFolder();
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, gmailPage.getMailSentPopupMessage()); ///check assertion  ///css = "div.vh>span.a8k"
        Assert.assertFalse(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),DRAFT_EMAIL_PRESENCE_ERR_MSG);  ///checked
    }
    protected void verifySentMailExistence(){
        gmailPageSteps.navigateToSentFolder();
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),EMPTY_SENT_FOLDER_ERR_MSG); ///checked
    }
}
