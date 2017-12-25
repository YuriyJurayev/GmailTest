package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;

import org.testng.annotations.Test;

public class GmailTest extends BaseTest {

    @Test
    public void composeAndSendEmail(){
        LoginPage loginPage = new LoginPage();
        User user = new User(PropertyProvider.getProperty("login"), PropertyProvider.getProperty("password"));
        Email email = new Email();
        email.setRecipients(PropertyProvider.getProperty("email_recipients"));
        email.setSubject(PropertyProvider.getProperty("email_subject"));
        email.setBody(PropertyProvider.getProperty("email_body"));
        loginPage.openLoginPage(PropertyProvider.getProperty("url"))
                .authorization(user)
                .composeEmail(email)
                .sendEmail();
        loginPage.logout();
    }

}
