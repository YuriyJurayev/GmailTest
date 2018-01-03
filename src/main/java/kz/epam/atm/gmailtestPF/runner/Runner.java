package kz.epam.atm.gmailtestPF.runner;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

public class Runner {

    public static void main(String[] args){
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add(SCENARIOS_PATH);
        testng.setTestSuites(suites);
        testng.setOutputDirectory(TEST_OUTPUT_FOLDER_PATH);
        testng.run();
    }
}
