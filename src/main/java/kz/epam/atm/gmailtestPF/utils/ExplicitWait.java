package kz.epam.atm.gmailtestPF.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {

    public static void explicitWaitVisibilityOfElement(WebDriver driver, int time, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void explicitWaitUntilElementDisappears(WebDriver driver,int time, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }
}
