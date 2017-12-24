package kz.epam.atm.gmailtestPF.utils;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {

    public static void explicitWaitVisibilityOfElement(int time, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(FactoryDriver.getInstance(), time);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void explicitWaitUntilElementToBeClickable(int time, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(FactoryDriver.getInstance(), time);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
