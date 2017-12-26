package kz.epam.atm.gmailtestPF.pages;


import kz.epam.atm.gmailtestPF.utils.ExplicitWait;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;



import java.util.List;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;

public class GmailPage extends AbstractPage{

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

    @FindBy(css = "div.J-N-JX.aDE.aDF")
    private WebElement contextDeleteEmailButton;

    public List<WebElement> getAllEmailsInFolder() {
        return this.AllEmailsInFolder;
    }

    @FindBys(@FindBy(xpath = "//div[@role='main']//table[@class='F cf zt']//tr"))
    private List<WebElement> AllEmailsInFolder;


    public GmailPage(){
        super();
    }

    public void clickComposeEmail(){
        composeEmailButton.click();
    }

    public void fillEmailRecipientsField(String recipients){
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, emailRecipientsField);
        emailRecipientsField.click();
        emailRecipientsField.sendKeys(recipients);
        new Actions(driver).sendKeys(emailRecipientsField, Keys.TAB).build().perform();
    }

    public void fillEmailBodyField(String body){
        emailBodyField.click();
        emailBodyField.sendKeys(body);
    }

    public void fillEmailSubjectField(String subject){
        emailSubjectField.click();
        emailSubjectField.sendKeys(subject);
    }

    public void clickEmailWindowCloseButton() {
        emailWindowCloseButton.click();
    }

    public void clickDraftFolderLink(){
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, draftFolderLink);
        draftFolderLink.click();
    }

    public void clickSentFolderLink(){
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, sentFolderLink);
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
    public WebElement getEmailSubjectOutputTextElement(){
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

    public void clickDeleteEmailButton(){
        deleteEmailButton.click();
    }
    public void clickDeleteFirstEmailButtonViaContextMenu(){
        new Actions(driver).contextClick(firstEmailInList).
                click(contextDeleteEmailButton).build().perform();
    }
    public void clickDeletionApplyButton(){
        deletionApplyButton.click();
    }


}
