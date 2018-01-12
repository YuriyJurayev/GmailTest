package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;

public abstract class AbstractSteps {

    protected void openPage(String url){
        FactoryDriver.getInstance().get(url);
    }
}
