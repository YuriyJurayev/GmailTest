package kz.epam.atm.gmailtestPF.step_definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.testng.Assert;

import java.util.Map;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.DRAFT_EMAIL_ABSENCE_ERR_MSG;

public class GmailSteps {

    private GmailPage gmailPage;
    private Email email;

    public GmailSteps(){
        gmailPage = new GmailPage();
    }

    @Given("^user clicks compose email button and fills recipients \"([^\"]*)\",subject \"([^\"]*)\",body \"([^\"]*)\",image \"([^\"]*)\"$")
    public void compose_new_email(String recipients,String subject,String body, String image){
        email = new Email
                .EmailBuilder(recipients,subject)
                .setBody(body)
                .setImage(image)
                .build();
        gmailPage.composeEmail(email);
    }

    @When("^user adds an image to email body$")
    public void add_image_to_email_body() {
        gmailPage.addImageToEmailBodyFromWeb(email);
    }

    @Then("^draft email is displayed in draft folder$")
    public void verify_draft_email_is_displayed_in_draft_folder() {
        gmailPage.clickDraftFolderLink();
        ExplicitWait.explicitWaitVisibilityOfElement(gmailPage.getDraftMailLabel());
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()), DRAFT_EMAIL_ABSENCE_ERR_MSG);
    }

}
