package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import kz.epam.atm.gmailtestPF.utils.RandomDataGenerator;
import kz.epam.atm.gmailtestPF.utils.ScreenshotExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;

public class GmailPageSteps extends AbstractSteps{

    private static final String DRAFT_MAIL_ABSENCE_ERR_MSG = "Draft mail not found.";
    private static final String INCORRECT_RECIPIENT_ERR_MSG = "Recipient is not equal.";
    private static final String INCORRECT_SUBJECT_ERR_MSG = "Subject is not equal.";
    private static final String INCORRECT_BODY_ERR_MSG = "Mail body is not equal.";
    private static final String DRAFT_MAIL_PRESENCE_ERR_MSG = "Found draft mail in the draft folder.";
    private static final String EMPTY_SENT_FOLDER_ERR_MSG = "Sent folder is empty.";
    private String subjectBuilder;
    private GmailPage gmailPage;
    private WebElement firstEmailLocator;

    public GmailPageSteps(){
        super();
        gmailPage = new GmailPage();
        firstEmailLocator = gmailPage.getFirstEmailInList();
    }


    public GmailPageSteps composeEmail(String recipients, String subject, String body) {
        gmailPage.clickComposeEmail();
        gmailPage.fillEmailRecipientsField(recipients);
        subjectBuilder = subject + "(" + RandomDataGenerator.generateRandomInt() + ")" ;
        gmailPage.fillEmailSubjectField(subjectBuilder);
        gmailPage.fillEmailBodyField(body);
        gmailPage.clickEmailWindowCloseButton();
        return this;
    }


    private String getEmailAttributeText(WebElement email,WebElement attribute){
        email.click();
        return attribute.getText();
    }
    public GmailPageSteps sendEmail(){
        gmailPage.clickSendEmail();
        return this;
    }

    public void deleteAllEmailsFromFolder(){
        gmailPage.clickSelectAllEmailsCheckbox();
        gmailPage.clickDeleteEmailButton();
        gmailPage.clickDeletionApplyButton();
        Assert.assertFalse(DOMElementPresence.isElementPresent(firstEmailLocator));
    }
    public void deleteFirstEmailFromFolder(){
        int numberOfEmailsBeforeDeletion = gmailPage.getAllEmailsInFolder().size();
        highlightElement(firstEmailLocator);
        ScreenshotExecutor.takeScreenshot();
        gmailPage.clickDeleteFirstEmailButtonViaContextMenu();
        gmailPage.clickDeletionApplyButton();
        ScreenshotExecutor.takeScreenshot();
        int numberOfEmailsAfterDeletion = gmailPage.getAllEmailsInFolder().size();
        Assert.assertEquals(numberOfEmailsBeforeDeletion,numberOfEmailsAfterDeletion + 1,"Email deletion failed.");
    }

    public GmailPageSteps verifyDraftMailExistence(String recipients,String body){
        gmailPage.navigateToDraftFolder();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, EXPLICIT_WAIT_TIMEOUT, gmailPage.getDrafMailLabel());
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),DRAFT_MAIL_ABSENCE_ERR_MSG); //checked
        Assert.assertEquals(getEmailAttributeText(gmailPage.getFirstEmailInList(),gmailPage.getEmailRecipientsOutputTextElement()), recipients,INCORRECT_RECIPIENT_ERR_MSG); ///check locators
        Assert.assertEquals(getEmailAttributeText(gmailPage.getFirstEmailInList(),gmailPage.getEmailSubjectOutputTextElementt()), subjectBuilder,INCORRECT_SUBJECT_ERR_MSG);
        Assert.assertEquals(getEmailAttributeText(gmailPage.getFirstEmailInList(),gmailPage.getEmailBodyField()), body,INCORRECT_BODY_ERR_MSG);
        return this;
    }
    public GmailPageSteps verifyDraftMailAbsence(){
        gmailPage.navigateToDraftFolder();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, EXPLICIT_WAIT_TIMEOUT, gmailPage.getMailSentPopupMessage()); ///check assertion  ///css = "div.vh>span.a8k"
        Assert.assertFalse(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),DRAFT_MAIL_PRESENCE_ERR_MSG);  ///checked
        return this;
    }
    public GmailPageSteps verifySentMailExistence(){
        gmailPage.navigateToSentFolder();
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),EMPTY_SENT_FOLDER_ERR_MSG); ///checked
        return this;
    }

}
