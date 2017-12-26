package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import kz.epam.atm.gmailtestPF.utils.RandomNumberGenerator;
import kz.epam.atm.gmailtestPF.utils.ScreenshotExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;

public class GmailPageSteps extends AbstractSteps{

    private String subjectBuilder;
    private GmailPage gmailPage;
    private WebElement firstEmailLocator;

    public GmailPageSteps(WebDriver driver){
        super(driver);
        gmailPage = new GmailPage(driver);
        firstEmailLocator = gmailPage.getFirstEmailInList();
    }

    public String getSubjectBuilder() {
        return subjectBuilder;
    }


    public GmailPageSteps composeEmail(Email email) {
        gmailPage.clickComposeEmail();
        gmailPage.fillEmailRecipientsField(email.getRecipients());
        subjectBuilder = email.getSubject() + "(" + RandomNumberGenerator.getRandomInt() + ")" ;
        gmailPage.fillEmailSubjectField(subjectBuilder);
        gmailPage.fillEmailBodyField(email.getBody());
        return this;
    }

    public GmailPageSteps closeEmailWindow(){
        gmailPage.clickEmailWindowCloseButton();
        return this;
    }
    public String getFirstEmailRecipientsFieldText(){
        gmailPage.getFirstEmailInList().click();
        WebElement recipientsField = gmailPage.getEmailRecipientsOutputTextElement();
        ExplicitWait.explicitWaitUntilElementToBeClickable(driver,EXPLICIT_WAIT_TIMEOUT, recipientsField);
        return recipientsField.getText();
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
    public void deleteFirstEmailFromFolder(){
        highlightElement(firstEmailLocator);
        ScreenshotExecutor.takeScreenshot(driver);
        gmailPage.clickDeleteFirstEmailButtonViaContextMenu();
        gmailPage.clickDeletionApplyButton();
        ScreenshotExecutor.takeScreenshot(driver);
    }

    public int countNumberOfEmailsInFolder(){
        return gmailPage.getAllEmailsInFolder().size();
    }
    public void navigateToDraftFolder(){
        gmailPage.clickDraftFolderLink();
    }
    public void navigateToSentFolder(){
        gmailPage.clickSentFolderLink();
    }



}
