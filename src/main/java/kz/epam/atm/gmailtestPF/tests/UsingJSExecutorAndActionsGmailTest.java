package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.Email;

import kz.epam.atm.gmailtestPF.property.PropertyProvider;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

import org.testng.Assert;
import org.testng.annotations.Test;



public class UsingJSExecutorAndActionsGmailTest extends BaseTest {


    public void verifyFirstEmailDeletion(int BeforeDeletion,int AfterDeletion ){
        Assert.assertEquals(BeforeDeletion,AfterDeletion + 1,EMAIL_DELETION_ERR_MSG);
    }

    @Test
    public void composeAndSendEmailUsingJSExecutorAndActions(){
        Email email = new Email();
        email.setRecipients(PropertyProvider.getProperty("second_email_recipients"));
        email.setSubject(PropertyProvider.getProperty("second_email_subject"));
        email.setBody(PropertyProvider.getProperty("second_email_body"));
        gmailPageSteps.composeEmail(email)
                .closeEmailWindow();
        verifyDraftMailExistence(email);
        gmailPageSteps.sendEmail();
        verifyDraftMailAbsence();
        verifySentMailExistence();
        int numberOfEmailsBeforeDeletion = gmailPageSteps.countNumberOfEmailsInFolder();
        gmailPageSteps.deleteFirstEmailFromFolder();
        int numberOfEmailsAfterDeletion = gmailPageSteps.countNumberOfEmailsInFolder();
        verifyFirstEmailDeletion(numberOfEmailsBeforeDeletion, numberOfEmailsAfterDeletion);


    }

}
