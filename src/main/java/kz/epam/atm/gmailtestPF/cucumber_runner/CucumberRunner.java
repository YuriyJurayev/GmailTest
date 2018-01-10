package kz.epam.atm.gmailtestPF.cucumber_runner;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.CUCUMBER_SCENARIOS_PATH;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.TEST_OUTPUT_FOLDER_PATH;

public class CucumberRunner {

    public static void main(String[] args){
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add(CUCUMBER_SCENARIOS_PATH);
        testng.setTestSuites(suites);
        testng.setOutputDirectory(TEST_OUTPUT_FOLDER_PATH);
        testng.run();
    }
}
