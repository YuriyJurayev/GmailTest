package kz.epam.atm.gmailtest.tests;

import kz.epam.atm.gmailtest.property.PropertyProvider;
import kz.epam.atm.gmailtest.steps.GmailPageSteps;
import kz.epam.atm.gmailtest.steps.LoginPageSteps;
import org.openqa.selenium.By;

import org.testng.annotations.Test;

public class GmailTest extends BaseTest{

    @Test
    public void gmailTest(){
        LoginPageSteps loginPage = new LoginPageSteps(driver);
        loginPage.openLoginPage(PropertyProvider.getProperty("url"));
        loginPage.authorization(PropertyProvider.getProperty("login"),PropertyProvider.getProperty("password"));
        GmailPageSteps gmailPage = new GmailPageSteps(driver);
        String recipietns = PropertyProvider.getProperty("email_recipients");
        String subject = PropertyProvider.getProperty("email_subject");
        String body = PropertyProvider.getProperty("email_body");
        gmailPage.composeEMail(recipietns,subject,body);
        gmailPage.verifyDraftMailExistence(recipietns,subject,body);
        gmailPage.sendMail();
        gmailPage.verifyDraftMailAbsence();
        gmailPage.verifySentMailExistence();
        gmailPage.deleteEmail(By.xpath("//div[@role='main']//table[@class='F cf zt']//tr[1]//div[@class='T-Jo-auh']"));
        loginPage.logout();
    }



}
