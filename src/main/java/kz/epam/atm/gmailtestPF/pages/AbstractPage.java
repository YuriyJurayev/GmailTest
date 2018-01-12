package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected AbstractPage() {
        PageFactory.initElements(FactoryDriver.getInstance(), this);
    }

}
