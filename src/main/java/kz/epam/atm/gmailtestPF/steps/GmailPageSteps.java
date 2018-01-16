package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;

public class GmailPageSteps extends AbstractSteps{

    private GmailPage gmailPage;

    public GmailPageSteps(){
        gmailPage = new GmailPage();
    }

    public String getSubjectContent() {
        return gmailPage.getSubjectContentString();
    }

    public GmailPageSteps composeNewEmail(Email email){
        log.info("compose new email");
        gmailPage.composeEmail(email);
        log.info("email fields are filled");
        closeEmailWindow();
        return this;
    }
    public GmailPageSteps composeNewEmailWithImage(Email email){
        log.info("compose new email");
        gmailPage.composeEmail(email);
        log.info("add an image to email body");
        gmailPage.addImageToEmailBodyFromWeb(email);
        log.info("email fields are filled");
        return this;
    }
    public GmailPageSteps sendCurrentEmail(){
        log.info("send email");
        gmailPage.sendEmail();
        return this;
    }
    private GmailPageSteps closeEmailWindow(){
        log.info("close email pop-up window");
        ExplicitWait.explicitWaitVisibilityOfElement(gmailPage.getChangesSavingSing());
        gmailPage.clickEmailWindowCloseButton();
        return this;
    }
    public String getFirstEmailRecipientsFieldText(){
        return gmailPage.getFirstEmailRecipientsText();
    }
    public String getFirstEmailSubjectFieldText(){
        return gmailPage.getFirstEmailSubjectText();
    }
    public String getFirstEmailBodyFieldText(){
        return gmailPage.getFirstEmailBodyText();
    }

    public void deleteAllEmailsFromSentFolder(){
        log.info("delete all emails from sent folder");
        gmailPage.deleteAllEmailsFromFolder();
    }
    public void navigateToDraftFolder(){
        gmailPage.clickDraftFolderLink();
    }
    public void navigateToSentFolder(){
        gmailPage.clickSentFolderLink();
    }
    public boolean isFirstEmailInListPresent(){
        return DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList());
    }
    public boolean isEmailListEmpty(){
        return DOMElementPresence.isElementPresent(gmailPage.getEmptyEmailListSign());
    }
    public boolean isImageInEmailBodyPresent(){
        return DOMElementPresence.isElementPresent(gmailPage.getImageInsideEmailBody());
    }
    public void waitUntilDraftEmailLabelAppears(){
        ExplicitWait.explicitWaitVisibilityOfElement(gmailPage.getDraftMailLabel());
    }
}
