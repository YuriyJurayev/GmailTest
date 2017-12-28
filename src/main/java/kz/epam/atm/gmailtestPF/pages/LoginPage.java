package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;

public class LoginPage extends AbstractPage{

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//div[@id='identifierNext']/content/span")
    private WebElement nextButtonEmailTab;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@id='passwordNext']/content/span")
    private WebElement nextButtonPasswordTab;

    @FindBy(css = "span.gbii")
    private WebElement googleAccountIcon;

    @FindBy(id = "gb_71")
    private WebElement logoutButton;

    @FindBy(css = "div.bdf4dc.slptg")
    private WebElement singInTab;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }
    public WebElement getSingInTab() {
        return singInTab;
    }
    public void fillEmailField(String username){
        ExplicitWait.explicitWaitVisibilityOfElement(driver, EXPLICIT_WAIT_TIMEOUT, emailField );
        emailField.clear();
        emailField.sendKeys(username);
    }
    public void clickNextButtonEmailTab(){
        nextButtonEmailTab.click();
    }
    public void fillPasswordField(String password){
        ExplicitWait.explicitWaitVisibilityOfElement(driver, EXPLICIT_WAIT_TIMEOUT, passwordField );
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickNextButtonPasswordTab(){
        nextButtonPasswordTab.click();
    }
    public void invokeGoogleAccountPopUpWindow(){
        ExplicitWait.explicitWaitUntilElementToBeClickable(driver, EXPLICIT_WAIT_TIMEOUT, googleAccountIcon);
        googleAccountIcon.click();
    }
    public void clickLogoutButton() {
        ExplicitWait.explicitWaitVisibilityOfElement(driver,EXPLICIT_WAIT_TIMEOUT, logoutButton);
        logoutButton.click();
    }
}