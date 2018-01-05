package kz.epam.atm.gmailtestPF.driver;

import org.openqa.selenium.remote.ExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;

import java.util.Map;

public class LocalExecuteMethod {

    CustomWebDriver driver;

    public LocalExecuteMethod(CustomWebDriver driver) {
        this.driver = driver;
    }
/*
    public Object execute(String commandName, Map<String, ?> parameters) {
        Response response;
        if (parameters != null && !parameters.isEmpty()) {
            response = this.driver.execute(commandName, parameters);
        } else {
            response = this.driver.execute(commandName);
        }

        return response.getValue();
    }*/
}
