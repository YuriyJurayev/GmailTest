package kz.epam.atm.gmailtestPF.tests;

import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;

import org.testng.annotations.Test;

public class GmailTest extends BaseTest {

    @Test
    public void gmailTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage(PropertyProvider.getProperty("url"));
        loginPage.authorization(PropertyProvider.getProperty("login"), PropertyProvider.getProperty("password"));
        GmailPage gmailPage = new GmailPage(driver);
        String recipietns = PropertyProvider.getProperty("email_recipients");
        String subject = PropertyProvider.getProperty("email_subject");
        String body = PropertyProvider.getProperty("email_body");
        gmailPage.composeEmail(recipietns,subject,body);
        gmailPage.verifyDraftMailExistence(recipietns,subject,body);
        gmailPage.sendMail();
        gmailPage.verifyDraftMailAbsence();
        gmailPage.verifySentMailExistence();
        gmailPage.deleteEmail();
        loginPage.logout();
    }



}