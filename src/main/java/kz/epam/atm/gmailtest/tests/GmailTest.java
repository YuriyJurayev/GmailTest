package kz.epam.atm.gmailtest.tests;

import kz.epam.atm.gmailtest.property.PropertyProvider;
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
        driver.findElement(By.linkText("Выйти")).click();
    }

    @Test
    public void gmailTest(){
        openLoginPage(PropertyProvider.getProperty("url"));
        authorization(PropertyProvider.getProperty("login"),PropertyProvider.getProperty("password"));
        Assert.assertTrue(isElementPresent());
        logout();
    }
}
