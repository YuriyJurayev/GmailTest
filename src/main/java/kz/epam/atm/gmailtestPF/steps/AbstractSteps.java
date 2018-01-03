package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.openqa.selenium.WebDriver;

public class AbstractSteps {

    protected WebDriver driver;

    protected AbstractSteps() {
        this.driver = FactoryDriver.getInstance();
    }

    protected void openPage(String url){
        driver.get(url);
    }
}
