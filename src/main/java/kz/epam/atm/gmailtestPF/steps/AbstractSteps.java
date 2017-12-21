package kz.epam.atm.gmailtestPF.steps;

import org.openqa.selenium.WebDriver;

public class AbstractSteps {

    protected WebDriver driver;

    protected AbstractSteps(WebDriver driver) {
        this.driver = driver;
    }
}
