package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import kz.epam.atm.gmailtestPF.utils.RandomDataGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;

public class GmailPage extends AbstractPage{

    private static final String DRAFT_MAIL_ABSENCE_ERR_MSG = "Draft mail not found.";
    private static final String INCORRECT_RECIPIENT_ERR_MSG = "Recipient is not equal.";
    private static final String INCORRECT_SUBJECT_ERR_MSG = "Subject is not equal.";
    private static final String INCORRECT_BODY_ERR_MSG = "Mail body is not equal.";
    private static final String DRAFT_MAIL_PRESENCE_ERR_MSG = "Found draft mail in the draft folder.";
    private static final String EMPTY_SENT_FOLDER_ERR_MSG = "Sent folder is empty.";
    private String subjectBuilder;

    @FindBy(xpath = "//div[@role='main']//table[@class='F cf zt']//tr[1]")
    private WebElement firstEmailInList;

    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#drafts']")
    private WebElement draftFolderLink;

    @FindBy(css = "div.z0>div")
    private WebElement composeEmailButton;

    @FindBy(css = "textarea.vO")
    private WebElement emailRecipientsField;

    @FindBy(css = "div.az9>span")
    private WebElement emailRecipientsOutputTextElement;

    @FindBy(css = "div.LW-avf")
    private WebElement emailBodyField;

    @FindBy(css = "input.aoT")
    private WebElement emailSubjectField;

    @FindBy(css = "div.aYF")
    private WebElement emailSubjectOutputTextElement;

    @FindBy(css = "img.Ha")
    private WebElement emailWindowCloseButton;

    @FindBy(css = "div.T-I.J-J5-Ji.aoO.T-I-atl.L3")
    private WebElement emailSendButton;

    @FindBy(xpath = "//div[@role='main']//div[@class='yW']/font")
    private WebElement drafMailLabel;

    @FindBy(css = "table.cf.TB td.TC")
    private WebElement emptyEmailListSign; /// no usage

    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#sent']")
    private WebElement sentFolderLink;

    @FindBy(css = "div[gh^='tm'] div[role^='presentation']")
    private WebElement selectAllEmailsCheckbox;

    @FindBy(css = "div[gh^='tm'] div.nX")
    private WebElement deleteEmailButton;

    @FindBy(css = "button.J-at1-atl")
    private WebElement deletionApplyButton;

    @FindBy(css = "div.vh>span.a8k")
    private WebElement mailSentPopupMessage;


    public GmailPage(WebDriver driver){
        super(driver);
    }

    public void clickComposeEmail(){
        composeEmailButton.click();
    }
    public void fillEmailRecipientsField(String recipients){
        ExplicitWait.explicitWaitVisibilityOfElement(driver, EXPLICIT_WAIT_TIMEOUT, emailRecipientsField);
        emailRecipientsField.click();
        emailRecipientsField.sendKeys(recipients);
    }

    public void fillEmailBodyField(String body){
        emailBodyField.click();
        emailBodyField.sendKeys(body);
    }

    public void fillEmailSubjectField(String subject){
        emailSubjectField.click();
        subjectBuilder = subject + "(" + RandomDataGenerator.generateRandomInt() + ")" ;
        emailSubjectField.sendKeys(subjectBuilder);
    }

    public void clickEmailWindowCloseButton() {
        emailWindowCloseButton.click();
    }

    public void navigateToDraftFolder(){
        draftFolderLink.click();
    }
    public void navigateToSentFolder(){
        sentFolderLink.click();
    }


    public WebElement getFirstEmailInList(){
        return this.firstEmailInList;
    }

    public void clickSendEmail(){
        emailSendButton.click();
    }
    public WebElement getEmailRecipientsOutputTextElement(){
        return this.emailRecipientsOutputTextElement;
    }
    public WebElement getEmailSubjectOutputTextElementt(){
        return this.emailSubjectOutputTextElement;
    }
    public WebElement getEmailBodyField(){
        return this.emailBodyField;
    }
    public WebElement getDrafMailLabel(){
        return this.drafMailLabel;
    }
    public WebElement getMailSentPopupMessage(){
        return this.mailSentPopupMessage;
    }

    public void clickSelectAllEmailsCheckbox(){
        selectAllEmailsCheckbox.click();
    }
    public void clickDeleteEmailButtonAndConfirm(){
        deleteEmailButton.click();
        deletionApplyButton.click();
    }


}
