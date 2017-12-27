package kz.epam.atm.gmailtestPF.utils;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.io.File;
import java.io.IOException;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

public class ScreenshotExecutor extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult testResult) {
        takeScreenshot();
    }

    public static void takeScreenshot() {
        File screenshot = ((TakesScreenshot) FactoryDriver.getInstance()).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_PATH_AND_NAME + scrCount + PNG;
            scrCount++;
            FileUtils.copyFile(screenshot, new File(screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
