package kz.epam.atm.gmailtestPF.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.driver.FactoryDriver;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.testng.Assert;
import java.util.List;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.IMAGE_ABSENCE_ERR_MSG;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.INCORRECT_BODY_ERR_MSG;

public class GmailCucumberSteps {

    private GmailPage gmailPage;
    private Email email;

    public GmailCucumberSteps(){
        gmailPage = new GmailPage();
    }

    @Given("^a web browser is at the Google mail home page$")
    public void should_be_home_page(){
        Assert.assertTrue(FactoryDriver.getInstance().getCurrentUrl().matches("^(https://mail\\.google\\.com).*"));
    }

    /*@When("^the user clicks compose the email button and fills recipients, the subject, the body")
    public void compose_new_email(List<String> emailInput){
        email = new Email
                .EmailBuilder(emailInput.get(0),emailInput.get(1))
                .setBody(emailInput.get(2))
                .build();
        gmailPage.composeEmail(email);
        gmailPage.clickEmailWindowCloseButton();
    }*/

    @When("^the user clicks compose the email button and fills recipients, the subject, the body.*")
    public void compose_new_email(List<String> emailInput){
        email = new Email
                .EmailBuilder(emailInput.get(0),emailInput.get(1))
                .setBody(emailInput.get(2))
                .build();
        gmailPage.composeEmail(email);
    }

    @When("^the user adds an image to the email body$")
    public void add_image_to_email_body(List<String> emailInput) {
        email.setImage(emailInput.get(0));
        gmailPage.addImageToEmailBodyFromWeb(email);
    }


    @Then("^the email should be displayed as the first in the draft folder$")
    public void verify_draft_email_is_displayed_as_first_in_draft_folder() {
        gmailPage.clickDraftFolderLink();
        ExplicitWait.explicitWaitVisibilityOfElement(gmailPage.getDraftMailLabel());
        Assert.assertTrue(isFirstEmailInListPresents(), DRAFT_EMAIL_ABSENCE_ERR_MSG);
    }

    @Then("^the email fields should be displayed with the equal values as user has entered$")
    public void verify_draft_email_equality() {
        Assert.assertEquals(gmailPage.getFirstEmailRecipientsText(), email.getRecipients(), INCORRECT_RECIPIENT_ERR_MSG);
        Assert.assertEquals(gmailPage.getFirstEmailSubjectText(), gmailPage.getSubjectContentString(), INCORRECT_SUBJECT_ERR_MSG);
        Assert.assertEquals(gmailPage.getFirstEmailBodyText(), email.getBody(), INCORRECT_BODY_ERR_MSG);
        if(email.getImage() != null){
            Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getImageInsideEmailBody()), IMAGE_ABSENCE_ERR_MSG);
        }
    }

    @Given("^draft email is opened and ready for sending$")
    public void open_draft_email() {
        ExplicitWait.explicitWaitUntilElementToBeClickable(gmailPage.getFirstEmailInList());
        gmailPage.openFirstEmailInList();
    }

    @When("^user clicks send button$")
    public void send_email() {
        gmailPage.sendEmail();
    }

    @Then("^the email shouldn't be displayed in the draft folder$")
    public void verify_email_absence_in_draft_folder() {
        gmailPage.clickDraftFolderLink();
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getEmptyEmailListSign()), DRAFT_EMAIL_PRESENCE_ERR_MSG);
    }

    @Then("^the email should be displayed in the sent folder$")
    public void verify_email_existence_in_sent_folder() {
        gmailPage.clickSentFolderLink();
        Assert.assertTrue(isFirstEmailInListPresents(), EMPTY_SENT_FOLDER_ERR_MSG);
    }
    private boolean isFirstEmailInListPresents(){
        return DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList());
    }
}
