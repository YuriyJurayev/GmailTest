package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;
import kz.epam.atm.gmailtestPF.utils.EmailCounter;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UsingJSExecutorAndActionsGmailTest extends BaseTest {

    private void verifyFirstEmailDeletion(int BeforeDeletion,int AfterDeletion ){
        Assert.assertEquals(BeforeDeletion,AfterDeletion + 1,EMAIL_DELETION_ERR_MSG);
    }
    @Test
    @Parameters({"login","password"})
    public void composeAndSendEmailUsingJSExecutorAndActions(String login, String password){
        Email email = new Email();
        email.setRecipients(PropertyProvider.getProperty("second_email_recipients"));
        email.setSubject(PropertyProvider.getProperty("second_email_subject"));
        email.setBody(PropertyProvider.getProperty("second_email_body"));
        login(PropertyProvider.getProperty(login), PropertyProvider.getProperty(password));
        gmailPageSteps.composeEmail(email)
                .closeEmailWindow();
        verifyDraftMailExistence(email);
        gmailPageSteps.sendEmail();
        verifyDraftMailAbsence();
        verifySentMailExistence();
        int numberOfEmailsBeforeDeletion = EmailCounter.countNumberOfEmailsInFolder(gmailPage);
        gmailPageSteps.deleteFirstEmailFromFolder();
        int numberOfEmailsAfterDeletion = EmailCounter.countNumberOfEmailsInFolder(gmailPage);
        verifyFirstEmailDeletion(numberOfEmailsBeforeDeletion, numberOfEmailsAfterDeletion);
        logout();
    }

}
