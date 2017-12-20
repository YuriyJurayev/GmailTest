package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


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
    private WebElement logoutBotton;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void openLoginPage(String url){
        driver.get(url);
    }

    public void authorization(String login, String password){
        emailField.sendKeys(login);
        nextButtonEmailTab.click();

        ExplicitWait.explicitWaitVisibilityOfElement(driver, 10, passwordField );
        passwordField.sendKeys(password);
        nextButtonPasswordTab.click();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, 10, googleAccountIcon);
        Assert.assertTrue(DOMElementPresence.isElementPresent(logoutBotton),"Login failed.");
    }

    public void logout() {
        googleAccountIcon.click();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, 10, logoutBotton);
        logoutBotton.click();
    }
}
