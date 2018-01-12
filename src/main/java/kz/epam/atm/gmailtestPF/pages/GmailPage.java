package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import kz.epam.atm.gmailtestPF.utils.GActions;
import kz.epam.atm.gmailtestPF.utils.RandomNumberGenerator;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

public class GmailPage extends AbstractPage{

    private String subjectContent;

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
    private WebElement emptyEmailListSign;

    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#sent']")
    private WebElement sentFolderLink;

    @FindBy(css = "div[gh^='tm'] div[role^='presentation']")
    private WebElement selectAllEmailsCheckbox;

    @FindBy(css = "div.ar7>div.Bn")
    private WebElement deleteDraftEmailButton;

    @FindBy(css = "div[gh^='tm'] div.nX")
    private WebElement deleteEmailButton;

    @FindBy(css = "button.J-at1-atl")
    private WebElement deletionApplyButton;

    @FindBy(css = "div.vh>span.a8k")
    private WebElement mailSentPopupMessage;

    @FindBy(css = "div.J-N-JX.aDE.aDF")
    private WebElement contextDeleteEmailButton;

    @FindBy(css = "div.a5.aaA.aMZ")
    private WebElement addImageIcon;

    @FindBy(xpath = "//div[@id=':6']/div")
    private WebElement fromInternetTab;

    @FindBy(css = "input.Io-uq-Qc")
    private WebElement insertLinkField;

    @FindBy(css = "div.a-b-c.d-u.d-u-F.Io-tb-hp-enabled")
    private WebElement addImageButton;

    @FindBy(css = "div.LW-avf img")
    private WebElement imageInsideEmailBody;

    @FindBy(css = "iframe.KA-JQ")
    private WebElement downloadImageIFrame;

    @FindBy(css = "span.oG.aOy")
    private WebElement changesSavingSign;

    public WebElement getEmptyEmailListSign() {
        return emptyEmailListSign;
    }
    public WebElement getFirstEmailInList(){
        return this.firstEmailInList;
    }
    public WebElement getChangesSavingSing() {
        return changesSavingSign;
    }
    public String getFirstEmailSubjectText(){
        firstEmailInList.click();
        ExplicitWait.explicitWaitVisibilityOfElement(emailSubjectOutputTextElement);
        return emailSubjectOutputTextElement.getText();
    }
    public String getFirstEmailBodyText(){
        firstEmailInList.click();
        return this.emailBodyField.getText();
    }
    public WebElement getDraftMailLabel(){
        return this.drafMailLabel;
    }
    public WebElement getImageInsideEmailBody(){
        return imageInsideEmailBody;
    }

    public void  clickEmailWindowCloseButton() {
        ExplicitWait.explicitWaitVisibilityOfElement(changesSavingSign);
        emailWindowCloseButton.click();
    }
    public void clickDraftFolderLink(){
        try {
            ExplicitWait.explicitWaitUntilElementToBeClickable(draftFolderLink);
            draftFolderLink.click();
        }catch (StaleElementReferenceException e){
            GActions.moveToElementAndClick(draftFolderLink);
        }
        ExplicitWait.explicitWaitUrlToBe(DRAFT_FOLDER_URL_REGEX);
    }
    public void clickSentFolderLink(){
        try{
            ExplicitWait.explicitWaitUntilElementToBeClickable(sentFolderLink);
            sentFolderLink.click();
        }catch (WebDriverException e){
            GActions.moveToElementAndClick(sentFolderLink);
        }
        ExplicitWait.explicitWaitUrlToBe(SENT_FOLDER_URL_REGEX);
    }
    public void openFirstEmailInList(){
        ExplicitWait.explicitWaitUntilElementToBeClickable(firstEmailInList);
        firstEmailInList.click();
    }
    public String getFirstEmailRecipientsText(){
        openFirstEmailInList();
        return this.emailRecipientsOutputTextElement.getText();
    }

    public void composeEmail(Email email) {
        composeEmailButton.click();
        ExplicitWait.explicitWaitVisibilityOfElement(emailRecipientsField);
        emailRecipientsField.click();
        emailRecipientsField.sendKeys(email.getRecipients());
        GActions.pressTabKey(emailRecipientsField);
        emailSubjectField.click();
        subjectContent = buildSubjectString(email);
        emailSubjectField.sendKeys(subjectContent);
        emailBodyField.click();
        emailBodyField.sendKeys(email.getBody());
    }
    public String getSubjectContentString() {
        return subjectContent;
    }

    private String buildSubjectString(Email email){
        return email.getSubject() + "(" + RandomNumberGenerator.getRandomInt() + ")" ;
    }
    public void sendEmail(){
        emailSendButton.click();
    }

    public void addImageToEmailBodyFromWeb(Email email){
        addImageIcon.click();
        ExplicitWait.explicitWaitFrameToBeAvailableAndSwitchToIt(downloadImageIFrame);
        ExplicitWait.explicitWaitVisibilityOfElement(fromInternetTab);
        fromInternetTab.click();
        GActions.sendText(insertLinkField, email.getImage());
        ExplicitWait.explicitWaitUntilElementToBeClickable(addImageButton);
        addImageButton.click();
        FactoryDriver.getInstance().switchTo().defaultContent();
    }
    public void deleteAllEmailsFromFolder(){
        selectAllEmailsCheckbox.click();
        ExplicitWait.explicitWaitUntilElementToBeClickable(deleteEmailButton);
        deleteEmailButton.click();
        deletionApplyButton.click();
    }

    public void deleteAllDraftEmailsFromFolder(){
        selectAllEmailsCheckbox.click();
        ExplicitWait.explicitWaitUntilElementToBeClickable(deleteDraftEmailButton);
        deleteDraftEmailButton.click();
    }
}
