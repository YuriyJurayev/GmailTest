package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected static Logger log = Logger.getRootLogger();

    protected AbstractPage() {
        PageFactory.initElements(FactoryDriver.getInstance(), this);
    }

}
