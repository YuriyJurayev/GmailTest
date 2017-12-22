package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AbstractSteps {

    protected WebDriver driver;

    protected AbstractSteps() {
        this.driver = FactoryDriver.getInstance();
    }

    protected void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid orange'", element);
    }

    protected void unHighlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);
    }
}
