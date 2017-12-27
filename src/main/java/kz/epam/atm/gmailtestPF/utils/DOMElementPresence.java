package kz.epam.atm.gmailtestPF.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
public class DOMElementPresence {

    public static boolean isElementPresent(WebElement webElement){
        try {
            return webElement.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}

