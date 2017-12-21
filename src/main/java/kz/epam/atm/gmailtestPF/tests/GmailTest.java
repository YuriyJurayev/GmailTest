package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;

import org.testng.annotations.Test;

public class GmailTest extends BaseTest {

    @Test
    public void gmailTest(){
        LoginPage loginPage = new LoginPage(driver);
        String recipietns = PropertyProvider.getProperty("email_recipients");
        String subject = PropertyProvider.getProperty("email_subject");
        String body = PropertyProvider.getProperty("email_body");
        loginPage.openLoginPage(PropertyProvider.getProperty("url"))
                .authorization(PropertyProvider.getProperty("login"), PropertyProvider.getProperty("password"))
                .composeEmail(recipietns,subject,body)
                .verifyDraftMailExistence(recipietns,body)
                .sendEmail()
                .verifyDraftMailAbsence()
                .verifySentMailExistence()
                .deleteEmail();
        loginPage.logout();
    }



}
