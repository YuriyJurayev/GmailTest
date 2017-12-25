package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.steps.GmailPageSteps;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;


public class LoginPage extends AbstractPage{

    private static final String LOGIN_FAIL_ERR_MSG = "Login failed.";

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//div[@id='identifierNext']/content/span")
    private WebElement nextButtonEmailTab;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@id='passwordNext']/content/span")
    private WebElement nextButtonPasswordTab;

    @FindBy(css = "span.gbii")
    private WebElement googleAccountIcon; /// no usage

    @FindBy(id = "gb_71")
    private WebElement logoutBotton;

    public LoginPage(){
        super();
    }

    public LoginPage openLoginPage(String url){
        driver.get(url);
        return this;
    }

    public GmailPageSteps authorization(User user){
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, emailField );
        emailField.sendKeys(user.getUsername());
        nextButtonEmailTab.click();
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, passwordField );
        passwordField.sendKeys(user.getPassword());
        nextButtonPasswordTab.click();
        Assert.assertTrue(DOMElementPresence.isElementPresent(logoutBotton),LOGIN_FAIL_ERR_MSG);
        return new GmailPageSteps();
    }

    public void logout() {
        ExplicitWait.explicitWaitUntilElementToBeClickable(EXPLICIT_WAIT_TIMEOUT, googleAccountIcon);
        googleAccountIcon.click();
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, logoutBotton);
        logoutBotton.click();
    }
}
