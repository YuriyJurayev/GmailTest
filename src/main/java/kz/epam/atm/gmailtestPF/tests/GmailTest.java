package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

public class GmailTest extends BaseTest {

    private Email email;

    @Test
    @Parameters({"username","password"})
    public void loginTest(String username,String password){
        login(new User(PropertyProvider.getProperty(username), PropertyProvider.getProperty(password)));
    }
    @Test(dependsOnMethods = {"loginTest"})
    @Parameters({"email_recipients","email_subject","email_body","email_image"})
    public void composeEmailTest(String recipients,String subject,String body, String image){
        email = new Email
                .EmailBuilder(recipients,subject)
                .setBody(body)
                .setImage(image)
                .build();
        gmailPageSteps.composeNewEmailWithImage(email);
        gmailPageSteps.navigateToDraftFolder();
        ExplicitWait.explicitWaitVisibilityOfElement(gmailPage.getDrafMailLabel());
        Assert.assertTrue(gmailPageSteps.isFirstEmailInListPresent(), DRAFT_EMAIL_ABSENCE_ERR_MSG);
    }
    @Test(dependsOnMethods = {"composeEmailTest"})
    public void DraftEmailEqualityTest(){
        Assert.assertEquals(gmailPageSteps.getFirstEmailRecipientsFieldText(), email.getRecipients(), INCORRECT_RECIPIENT_ERR_MSG);
        Assert.assertEquals(gmailPageSteps.getFirstEmailSubjectFieldText(), gmailPageSteps.getSubjectContent(), INCORRECT_SUBJECT_ERR_MSG);
        Assert.assertEquals(gmailPageSteps.getFirstEmailBodyFieldText(), email.getBody(), INCORRECT_BODY_ERR_MSG);
        Assert.assertTrue(gmailPageSteps.isImageInEmailBodyPresent(),IMAGE_ABSENCE_ERR_MSG);
    }
    @Test(dependsOnMethods = {"DraftEmailEqualityTest"})
    public void sendEMailTest(){
        gmailPageSteps.sendCurrentEmail();
        gmailPageSteps.navigateToDraftFolder();
        Assert.assertTrue(gmailPageSteps.isEmailListEmpty(),DRAFT_EMAIL_PRESENCE_ERR_MSG);
    }

    @Test(dependsOnMethods = {"sendEMailTest"})
    protected void sentEmailExistenceTest(){
        gmailPageSteps.navigateToSentFolder();
        Assert.assertTrue(gmailPageSteps.isFirstEmailInListPresent(),EMPTY_SENT_FOLDER_ERR_MSG);
    }

    @Test(dependsOnMethods = {"sentEmailExistenceTest"})
    public void deleteAllEmailsFromSentFolderTest(){
        gmailPageSteps.deleteAllEmailsFromSentFolder();
        gmailPageSteps.navigateToSentFolder();
        Assert.assertFalse(gmailPageSteps.isFirstEmailInListPresent(),SENT_EMAIL_PRESENCE_ERR_MSG);
    }

    @Test(dependsOnMethods = {"deleteAllEmailsFromSentFolderTest"})
    public void logoutTest(){
        logout();
    }

}
