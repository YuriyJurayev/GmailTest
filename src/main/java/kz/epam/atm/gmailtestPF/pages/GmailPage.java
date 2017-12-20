package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import kz.epam.atm.gmailtestPF.utils.RandomDataGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class GmailPage extends AbstractPage{

    @FindBy(xpath = "//div[@role='main']//table[@class='F cf zt']//tr[1]")
    private WebElement firstEmailInList;

    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#drafts']")
    private WebElement draftFolderLink;

    @FindBy(css = "div.z0>div")
    private WebElement composeEmailButton;

    @FindBy(css = "textarea.vO")
    private WebElement emailRecipientsField; ///By.cssSelector("div.az9>span")

    @FindBy(css = "div.az9>span")
    private WebElement emailRecipientsOutputTextElement;

    @FindBy(css = "div.LW-avf")
    private WebElement emailBodyField;

    @FindBy(css = "input.aoT")
    private WebElement emailSubjectField;  ///By.cssSelector("div.aYF")

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

    @FindBy(css = "div[gh^='tm'] div.nX")
    private WebElement deleteEmailButton;

    @FindBy(css = "button.J-at1-atl")
    private WebElement deletionApplyButton;

    private int subjectID;

    public GmailPage(WebDriver driver){
        super(driver);
    }

    public void composeEmail(String recipients,String subject,String body) {
        composeEmailButton.click();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, 10, emailRecipientsField);
        emailRecipientsField.click();
        emailRecipientsField.sendKeys(recipients);
        emailBodyField.click();
        emailBodyField.sendKeys(body);
        emailSubjectField.click();
        subjectID = RandomDataGenerator.generateRandomInt();
        emailSubjectField.sendKeys(subject + "(" + subjectID + ")");
        emailWindowCloseButton.click();
    }
    private void navigateToMailBoxFolder(WebElement webElement){
        webElement.click();
    }

    private String getEmailAttributeText(WebElement email,WebElement attribute){
        email.click();
        return attribute.getText();
    }
    public void sendMail(){
        emailSendButton.click();
    }


    public void verifyDraftMailExistence(String recipients,String subject,String body){
        navigateToMailBoxFolder(draftFolderLink);
        ExplicitWait.explicitWaitVisibilityOfElement(driver, 10, drafMailLabel);
        Assert.assertTrue(DOMElementPresence.isElementPresent(firstEmailInList),"Draft mail not found."); //checked
        Assert.assertEquals(getEmailAttributeText(firstEmailInList,emailRecipientsOutputTextElement), recipients,"Recipient is not equal."); ///check locators
        Assert.assertEquals(getEmailAttributeText(firstEmailInList,emailSubjectOutputTextElement), subject + "(" + subjectID + ")","Subject is not equal.");
        Assert.assertEquals(getEmailAttributeText(firstEmailInList,emailBodyField), body,"Mail body is not equal.");
    }
    public void verifyDraftMailAbsence(){
        navigateToMailBoxFolder(draftFolderLink);
        ExplicitWait.explicitWaitVisibilityOfElement(driver,5,firstEmailInList); ///check assertion  ///css = "div.vh>span.a8k"
        Assert.assertFalse(DOMElementPresence.isElementPresent(firstEmailInList),"Found draft mail in the draft folder.");  ///checked
    }
    public void verifySentMailExistence(){
        navigateToMailBoxFolder(sentFolderLink);
        Assert.assertTrue(DOMElementPresence.isElementPresent(firstEmailInList),"Sent folder is empty."); ///checked
    }
    public void deleteEmail(){
        selectAllEmailsCheckbox.click();
        deleteEmailButton.click();
        deletionApplyButton.click();
        Assert.assertFalse(DOMElementPresence.isElementPresent(firstEmailInList));
    }
}
