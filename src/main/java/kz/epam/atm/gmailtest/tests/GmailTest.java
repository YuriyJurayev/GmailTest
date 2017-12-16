package kz.epam.atm.gmailtest.tests;

import kz.epam.atm.gmailtest.property.PropertyProvider;
import kz.epam.atm.gmailtest.utils.DOMElementPresence;
import kz.epam.atm.gmailtest.utils.ExplicitWait;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GmailTest extends BaseTest{

    public void openLoginPage(String url){
        driver.get(url);
    }

    public void authorization(String login, String password){
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(login);
        driver.findElement(By.xpath("//div[@id='identifierNext']/content/span")).click();
        ExplicitWait.explicitWaitForElement(driver, 10, By.xpath("//input[@type='password']"));
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//div[@id='passwordNext']/content/span")).click();
    }


    public void logout() {
        driver.findElement(By.cssSelector("span.gbii")).click();
        ExplicitWait.explicitWaitForElement(driver, 10, By.id("gb_71"));
        driver.findElement(By.id("gb_71")).click();
    }
    public void composeEMail(String recipients,String subject,String body){
        driver.findElement(By.cssSelector("div.z0>div")).click();
        ExplicitWait.explicitWaitForElement(driver, 10, By.cssSelector("textarea.vO"));
        driver.findElement(By.cssSelector("textarea.vO")).click();
        driver.findElement(By.cssSelector("textarea.vO")).sendKeys(recipients);
        driver.findElement(By.cssSelector("input.aoT")).click();
        driver.findElement(By.cssSelector("input.aoT")).sendKeys(subject);
        driver.findElement(By.cssSelector("div.LW-avf")).click();
        driver.findElement(By.cssSelector("div.LW-avf")).sendKeys(body);
        driver.findElement(By.cssSelector("div.T-I.J-J5-Ji.aoO.T-I-atl.L3")).click();


    }
    @Test
    public void gmailTest(){
        openLoginPage(PropertyProvider.getProperty("url"));
        authorization(PropertyProvider.getProperty("login"),PropertyProvider.getProperty("password"));
        Assert.assertTrue(DOMElementPresence.isElementPresent(driver,By.id("gb_71")));
        composeEMail("yuriy_jurayev@epam.com","This is the test mail","Some text inside the mail body");
        logout();
    }
}
