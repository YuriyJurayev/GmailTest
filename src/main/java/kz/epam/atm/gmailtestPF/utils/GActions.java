package kz.epam.atm.gmailtestPF.utils;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GActions {

    private static Actions actions = new Actions(FactoryDriver.getInstance());

    public static void pressTabKey(WebElement webElement){
        actions.sendKeys(webElement, Keys.TAB).build().perform();
    }
    public static void moveToElementAndClick(WebElement webElement){
        actions.moveToElement(webElement).click().perform();
    }
    public static void sendText(WebElement webElement,String text){
        actions.sendKeys(webElement, text).build().perform();
    }

}
