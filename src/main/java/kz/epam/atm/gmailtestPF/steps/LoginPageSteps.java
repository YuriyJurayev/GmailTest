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
        log.info("try to open the Login page");
        openPage(url);
        log.info("on the Login page");
        return this;
    }
    public GmailPageSteps authorization(User user){
        log.info("try to login");
        loginPage.login(user);
        return new GmailPageSteps();
    }
    public void logout() {
        log.info("try to logout");
        loginPage.logout();
    }

    public boolean isLogoutButtonPresent(){
        return DOMElementPresence.isElementPresent(loginPage.getLogoutButton());
    }

}
