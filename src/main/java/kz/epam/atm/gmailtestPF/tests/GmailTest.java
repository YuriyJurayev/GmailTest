package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.Email;

import kz.epam.atm.gmailtestPF.property.PropertyProvider;

import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;


import org.testng.Assert;
import org.testng.annotations.Test;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;


public class GmailTest extends BaseTest {


    public void verifySentMailAbsence(){
        gmailPageSteps.navigateToSentFolder();
        Assert.assertFalse(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),SENT_EMAIL_PRESENCE_ERR_MSG); ///checked
    }
    @Test
    public void composeAndSendEmail(){
        Email email = new Email();
        email.setRecipients(PropertyProvider.getProperty("first_email_recipients"));
        email.setSubject(PropertyProvider.getProperty("first_email_subject"));
        email.setBody(PropertyProvider.getProperty("first_email_body"));
        gmailPageSteps.composeEmail(email)
                .closeEmailWindow();
        verifyDraftMailExistence(email);
        gmailPageSteps.sendEmail();
        verifyDraftMailAbsence();
        verifySentMailExistence();
        gmailPageSteps.deleteAllEmailsFromFolder();
        verifySentMailAbsence();

    }


}
