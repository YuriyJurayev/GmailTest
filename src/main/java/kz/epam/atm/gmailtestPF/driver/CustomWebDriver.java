package kz.epam.atm.gmailtestPF.driver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.Sequence;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomWebDriver extends AbstractWebDriver {

    public CustomWebDriver(FirefoxDriver driver){
        super(driver);
    }
    public CustomWebDriver(ChromeDriver driver){
        super(driver);
    }
    @Override
    public void get(String url) {
        remoteWebDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return remoteWebDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return remoteWebDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return remoteWebDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        WebElement webElement = remoteWebDriver.findElement(by);
        executeScript("arguments[0].style.border='2px solid blue'", webElement);
        return webElement;
    }

    @Override
    public String getPageSource() {
        return remoteWebDriver.getPageSource();
    }

    @Override
    public void close() {
        remoteWebDriver.close();

    }

    @Override
    public void quit() {
        remoteWebDriver.close();

    }

    @Override
    public Set<String> getWindowHandles() {
        return remoteWebDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return remoteWebDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return remoteWebDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return remoteWebDriver.navigate();
    }

    @Override
    public Options manage() {
        return remoteWebDriver.manage();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return remoteWebDriver.executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return remoteWebDriver.executeAsyncScript(script, args);
    }

    @Override
    public Keyboard getKeyboard() {
        return remoteWebDriver.getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return remoteWebDriver.getMouse();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target)
            throws WebDriverException {
        return remoteWebDriver.getScreenshotAs(target);
    }
    @Override
    public void resetInputState() {
        remoteWebDriver.resetInputState();
    }

    @Override
    public void perform(Collection<Sequence> actions) {
        remoteWebDriver.perform(actions);
    }


}
