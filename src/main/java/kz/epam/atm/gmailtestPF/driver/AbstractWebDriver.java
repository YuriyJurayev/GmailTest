package kz.epam.atm.gmailtestPF.driver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.remote.*;


public abstract class AbstractWebDriver implements WebDriver, JavascriptExecutor, HasInputDevices, Interactive, TakesScreenshot{

    protected RemoteWebDriver remoteWebDriver;

    protected AbstractWebDriver(RemoteWebDriver driver){
        remoteWebDriver = driver;
    }
}

