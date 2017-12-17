package kz.epam.atm.gmailtest.tests;

import kz.epam.atm.gmailtest.property.PropertyProvider;
import kz.epam.atm.gmailtest.utils.DOMElementPresence;
import kz.epam.atm.gmailtest.utils.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;


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
    public void composeEMail(String recipients,String subject,String body) {
        driver.findElement(By.cssSelector("div.z0>div")).click();
        ExplicitWait.explicitWaitForElement(driver, 10, By.cssSelector("textarea.vO"));
        driver.findElement(By.cssSelector("textarea.vO")).click();
        driver.findElement(By.cssSelector("textarea.vO")).sendKeys(recipients);
        driver.findElement(By.cssSelector("input.aoT")).click();
        driver.findElement(By.cssSelector("input.aoT")).sendKeys(subject);
        driver.findElement(By.cssSelector("div.LW-avf")).click();
        driver.findElement(By.cssSelector("div.LW-avf")).sendKeys(body);
        driver.findElement(By.cssSelector("img.Ha")).click();
    }
    public void navigateToMailBoxFolder(By by){
        driver.findElement(by).click();
    }

    public String getDraftMailAttributeText(By mailLocator,By attributeLocator){
        ExplicitWait.explicitWaitForClickableElement(driver, 10, By.xpath("//div[@class='nH']/div[@role='main']//tbody/tr[1]"));
        ExplicitWait.explicitWaitForElement(driver, 10, By.xpath("//div[@class='nH']/div[@role='main']//tbody/tr[1]"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(mailLocator).click();   ///table.F.cf.zt tr.zA.yO" "//table[@class='F cf zt']/tbody/tr[@class='zA yO aqw']"
        return driver.findElement(attributeLocator).getText();

    }
    public void sendMail(){
        driver.findElement(By.cssSelector("div.T-I.J-J5-Ji.aoO.T-I-atl.L3")).click();

    }

    @Test
    public void gmailTest(){
        openLoginPage(PropertyProvider.getProperty("url"));
        authorization(PropertyProvider.getProperty("login"),PropertyProvider.getProperty("password"));
        Assert.assertTrue(DOMElementPresence.isElementPresent(driver,By.id("gb_71")),"Login failed");
        composeEMail("yuriy_jurayev@epam.com","This is the test mail","Some text inside the mail body");
        navigateToMailBoxFolder(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#drafts']"));
        Assert.assertTrue(DOMElementPresence.isElementPresent(driver,By.xpath("//div[@role='main']//tr[1]")),"Draft mail not found");
        Assert.assertEquals(getDraftMailAttributeText(By.xpath("//div[@role='main']//tr[1]"),By.cssSelector("div.oL.aDm.az9>span")),"yuriy_jurayev@epam.com","Recipient is not equal");
        Assert.assertEquals(getDraftMailAttributeText(By.xpath("//div[@role='main']//tr[1]"),By.cssSelector("div.aYF")),"This is the test mail","Subject is not equal");
        Assert.assertEquals(getDraftMailAttributeText(By.xpath("//div[@role='main']//tr[1]"),By.cssSelector("div.LW-avf")),"Some text inside the mail body","Mail body is not equal");
        sendMail();
        navigateToMailBoxFolder(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#drafts']"));
        Assert.assertTrue(DOMElementPresence.isElementPresent(driver,By.xpath("//div[@role='main']//table[@class='cF TB']/tbody/tr")),"Found draft mail in the draft folder");
        logout();
    }
}
