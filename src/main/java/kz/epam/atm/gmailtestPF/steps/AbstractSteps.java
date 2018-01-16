package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.apache.log4j.Logger;


public abstract class AbstractSteps {

    protected static Logger log = Logger.getRootLogger();

    protected void openPage(String url){
        FactoryDriver.getInstance().get(url);
    }
}
