package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private WebElement googleAccountIcon; /// no usage

    @FindBy(id = "gb_71")
    private WebElement logoutButton;

    @FindBy(css = "div.bdf4dc.slptg")
    private WebElement singInTab;

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public void login(User user){
        ExplicitWait.explicitWaitVisibilityOfElement(emailField);
        emailField.clear();
        emailField.sendKeys(user.getUsername());
        nextButtonEmailTab.click();
        ExplicitWait.explicitWaitVisibilityOfElement(passwordField);
        passwordField.clear();
        passwordField.sendKeys(user.getPassword());
        nextButtonPasswordTab.click();
    }
    public void logout(){
        ExplicitWait.explicitWaitUntilElementToBeClickable(googleAccountIcon);
        googleAccountIcon.click();
        ExplicitWait.explicitWaitVisibilityOfElement(logoutButton);
        logoutButton.click();
    }
}
