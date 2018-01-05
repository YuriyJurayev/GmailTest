package kz.epam.atm.gmailtestPF.step_definitions;

import cucumber.api.PendingException;
import kz.epam.atm.gmailtestPF.bo.User;
import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.property.PropertyProvider;
import kz.epam.atm.gmailtestPF.steps.GmailPageSteps;
import kz.epam.atm.gmailtestPF.steps.LoginPageSteps;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import org.testng.Assert;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static kz.epam.atm.gmailtestPF.property.GlobalConstants.LOGIN_FAIL_ERR_MSG;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.LOGOUT_FAIL_ERR_MSG;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps(){
        loginPage = new LoginPage();
    }


    @Given("^user opens gmail home page$")
    public void open_page(){
        FactoryDriver.getInstance().get(PropertyProvider.getProperty("url"));
    }
    @When("^user enters credentials$")
    public void enter_credentials(){
        loginPage.login(new User(PropertyProvider.getProperty("username"),PropertyProvider.getProperty("password")));
    }
    @Then("^gmail home page is displayed$")
    public void verify_login_is_successful(){
        Assert.assertTrue(DOMElementPresence.isElementPresent(loginPage.getLogoutButton()), LOGIN_FAIL_ERR_MSG);
    }

    @When("^user clicks logout button$")
    public void click_logout_button(){
        loginPage.logout();
    }
    @Then("^gmail sign in page is displayed$")
    public void verify_logout_is_successful(){
        Assert.assertFalse(DOMElementPresence.isElementPresent(loginPage.getLogoutButton()), LOGIN_FAIL_ERR_MSG);
    }
}
