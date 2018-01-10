package kz.epam.atm.gmailtestPF.tests_cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;

@CucumberOptions(strict = true, plugin = {"pretty"}, tags = {"~@smoke_test"}, features = "src/main/resources/features", glue = {
        "kz.epam.atm.gmailtestPF.step_definitions" },snippets = SnippetType.UNDERSCORE)
public class GmailSanityTest extends BaseCucumberTest{

}
