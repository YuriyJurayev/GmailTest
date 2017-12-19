package kz.epam.atm.gmailtest.runner;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args){
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("scenarios/testng.xml");
        testng.setTestSuites(suites);
        testng.setOutputDirectory("target/test-output");
        testng.run();
    }
}
