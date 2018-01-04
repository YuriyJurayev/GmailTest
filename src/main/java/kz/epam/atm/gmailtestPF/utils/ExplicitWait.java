package kz.epam.atm.gmailtestPF.utils;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;

public class ExplicitWait {

    private static WebDriverWait wait = new WebDriverWait(FactoryDriver.getInstance(), EXPLICIT_WAIT_TIMEOUT);

    public static void explicitWaitVisibilityOfElement(WebElement webElement) {
        wait.ignoring(StaleElementReferenceException.class, WebDriverException.class).until(ExpectedConditions.visibilityOf(webElement));
    }
    public static void explicitWaitUntilElementToBeClickable( WebElement webElement) {
        wait.ignoring(StaleElementReferenceException.class, WebDriverException.class).until(ExpectedConditions.elementToBeClickable(webElement));
    }
    public static void explicitWaitFrameToBeAvailableAndSwitchToIt( WebElement webElement) {
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
    }
}
