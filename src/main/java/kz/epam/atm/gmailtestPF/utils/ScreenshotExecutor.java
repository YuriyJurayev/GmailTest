package kz.epam.atm.gmailtestPF.utils;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.io.File;
import java.io.IOException;


public class ScreenshotExecutor extends TestListenerAdapter {

    private static final String SCREENSHOTS_PATH_AND_NAME = "./target/screenshots/screenshot-";
    private static final String PNG = ".png";
    private static int scrCount = 0;

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
