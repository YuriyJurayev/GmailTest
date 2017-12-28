package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;


public class GmailTest extends BaseTest {

    private void verifySentMailAbsence(){
        gmailPageSteps.navigateToSentFolder();
        Assert.assertFalse(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),SENT_EMAIL_PRESENCE_ERR_MSG);
    }
    @Test
    @Parameters({"login","password"})
    public void composeAndSendEmail(String login,String password){
        Email email = new Email();
        email.setRecipients(PropertyProvider.getProperty("first_email_recipients"));
        email.setSubject(PropertyProvider.getProperty("first_email_subject"));
        email.setBody(PropertyProvider.getProperty("first_email_body"));
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