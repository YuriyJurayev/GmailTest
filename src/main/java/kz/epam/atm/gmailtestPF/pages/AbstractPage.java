package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {

    protected WebDriver driver;

    protected AbstractPage() {
        this.driver = FactoryDriver.getInstance();
        PageFactory.initElements(driver, this);
    }

}
