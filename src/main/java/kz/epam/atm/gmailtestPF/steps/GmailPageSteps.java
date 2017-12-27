package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.SubjectBuilder;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class GmailPageSteps extends AbstractSteps{

    private String subjectContent;
    private GmailPage gmailPage;
    private WebElement firstEmailLocator;

    public GmailPageSteps(){
        super();
        gmailPage = new GmailPage();
        firstEmailLocator = gmailPage.getFirstEmailInList();
    }

    public String getSubjectContent() {
        return subjectContent;
    }

    public GmailPageSteps composeEmail(Email email) {
        gmailPage.clickComposeEmail();
        gmailPage.fillEmailRecipientsField(email.getRecipients());
        subjectContent = SubjectBuilder.buildSubjectString(email);
        gmailPage.fillEmailSubjectField(subjectContent);
        gmailPage.fillEmailBodyField(email.getBody());
        return this;
    }
    public GmailPageSteps closeEmailWindow(){
        gmailPage.clickEmailWindowCloseButton();
        return this;
    }
    public String getFirstEmailRecipientsFieldText(){
        gmailPage.getFirstEmailInList().click();
        return gmailPage.getEmailRecipientsOutputTextElement().getText();
    }
    public String getFirstEmailSubjectFieldText(){
        gmailPage.getFirstEmailInList().click();
        return gmailPage.getEmailSubjectOutputTextElement().getText();
    }
    public String getFirstEmailBodyFieldText(){
        gmailPage.getFirstEmailInList().click();
        return gmailPage.getEmailBodyField().getText();
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
    public void navigateToDraftFolder(){
        gmailPage.clickDraftFolderLink();
    }
    public void navigateToSentFolder(){
        gmailPage.clickSentFolderLink();
    }



}
