package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.pages.LoginPage;

public class LoginPageSteps extends AbstractSteps{

    private LoginPage loginPage;

    public LoginPageSteps(){
        super();
        loginPage = new LoginPage();
    }
    public LoginPageSteps openLoginPage(String url){
        driver.get(url);
        return this;
    }
    public GmailPageSteps authorization(User user){
        loginPage.fillEmailField(user.getUsername());
        loginPage.clickNextButtonEmailTab();
        loginPage.fillPasswordField(user.getPassword());
        loginPage.clickNextButtonPasswordTab();
        return new GmailPageSteps();
    }
    public void logout() {
        loginPage.invokeGoogleAccountPopUpWindow();
        loginPage.clickLogoutButton();
    }
}
