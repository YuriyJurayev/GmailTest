package kz.epam.atm.gmailtestPF.steps;
/*
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageSteps {

    private WebDriver driver;

    public LoginPageSteps(WebDriver driver){
        this.driver = driver;
    }

    public void openLoginPage(String url){
        driver.get(url);
    }

    public void authorization(String login, String password){
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(login);
        driver.findElement(By.xpath("//div[@id='identifierNext']/content/span")).click();
        By passwordField = By.xpath("//input[@type='password']");
        ExplicitWait.explicitWaitVisibilityOfElement(driver, 10, passwordField );
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(By.xpath("//div[@id='passwordNext']/content/span")).click();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, 10, By.cssSelector("span.gbii"));
        Assert.assertTrue(DOMElementPresence.isElementPresent(driver,By.id("gb_71")),"Login failed.");
    }

    public void logout() {
        driver.findElement(By.cssSelector("span.gbii")).click();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, 10, By.id("gb_71"));
        driver.findElement(By.id("gb_71")).click();
    }
}*/
