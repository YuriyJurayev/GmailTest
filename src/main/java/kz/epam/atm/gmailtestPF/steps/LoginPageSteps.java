package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;

public class LoginPageSteps extends AbstractSteps{

    private LoginPage loginPage;

    public LoginPageSteps(){
        loginPage = new LoginPage();
    }
    public LoginPageSteps openLoginPage(String url){
        openPage(url);
        return this;
    }
    public GmailPageSteps authorization(User user){
        loginPage.login(user);
        return new GmailPageSteps();
    }
    public void logout() {
        loginPage.logout();
    }

    public boolean isLogoutButtonPresent(){
        return DOMElementPresence.isElementPresent(loginPage.getLogoutButton());
    }
}
