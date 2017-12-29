package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import kz.epam.atm.gmailtestPF.utils.SubjectBuilder;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;

public class GmailPageSteps extends AbstractSteps{

    private String subjectContent;
    private GmailPage gmailPage;

    public GmailPageSteps(){
        super();
        gmailPage = new GmailPage();
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
    public GmailPageSteps addImageToEmailBodyFromWeb(Email email){
        gmailPage.clickAddImageIcon();
        ExplicitWait.explicitWaitFrameToBeAvailableAndSwitchToIt(EXPLICIT_WAIT_TIMEOUT,gmailPage.getDownloadImageIFrame());
        gmailPage.clickFromInternetTab();
        gmailPage.fillInsertLinkField(email.getImage());
        gmailPage.clickAddImageButton();
        driver.switchTo().defaultContent();
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
    }
    public void navigateToDraftFolder(){
        gmailPage.clickDraftFolderLink();
    }
    public void navigateToSentFolder(){
        gmailPage.clickSentFolderLink();
    }
}
