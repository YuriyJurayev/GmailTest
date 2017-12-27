package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

public class GmailTest extends BaseTest {


    protected void verifyDraftMailExistence(Email email){
        gmailPageSteps.navigateToDraftFolder();
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, gmailPage.getDrafMailLabel());
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
    public void verifySentMailAbsence(){
        gmailPageSteps.navigateToSentFolder();
        Assert.assertFalse(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),SENT_EMAIL_PRESENCE_ERR_MSG);
    }
    @Test
    @Parameters({"login","password"})
    public void composeAndSendEmail(String login,String password){
        Email email = new Email();
        email.setRecipients(PropertyProvider.getProperty("email_recipients"));
        email.setSubject(PropertyProvider.getProperty("email_subject"));
        email.setBody(PropertyProvider.getProperty("email_body"));
        login(PropertyProvider.getProperty(login), PropertyProvider.getProperty(password));
        gmailPageSteps.composeEmail(email)
                .closeEmailWindow();
        verifyDraftMailExistence(email);
        gmailPageSteps.sendEmail();
        verifyDraftMailAbsence();
        verifySentMailExistence();
        gmailPageSteps.deleteAllEmailsFromFolder();
        verifySentMailAbsence();
        logout();

    }


}
