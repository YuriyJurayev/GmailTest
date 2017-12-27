package kz.epam.atm.gmailtestPF.utils;

import kz.epam.atm.gmailtestPF.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.io.File;
import java.io.IOException;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

public class ScreenshotExecutor extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult testResult) {
         Object instance = testResult.getInstance();
         if(instance instanceof BaseTest){
             BaseTest test = ((BaseTest) testResult.getInstance());
             takeScreenshot(test.getDriver());
         }

    }
    public static void takeScreenshot(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_PATH_AND_NAME + scrCount + PNG;
            scrCount++;
            FileUtils.copyFile(screenshot, new File(screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
