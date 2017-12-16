package kz.epam.atm.gmailtest.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DOMElementPresence {

    public static boolean isElementPresent(WebDriver driver,By by){
        return driver.findElements(by).size() > 0;
    }
}
