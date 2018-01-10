package kz.epam.atm.gmailtestPF.step_definitions;

import cucumber.api.java.Before;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import org.testng.Assert;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;

public class CucumberHooks {

    private GmailPage gmailPage;

    public CucumberHooks(){
        gmailPage = new GmailPage();
    }

    @Before(value = "@smoke_test,@sanity_test",order = 1)
    public void cleanUpDraftFolder(){
        gmailPage.clickDraftFolderLink();
        if(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList())){
            gmailPage.deleteAllDraftEmailsFromFolder();
            gmailPage.clickDraftFolderLink();
            Assert.assertFalse(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),DRAFT_EMAIL_PRESENCE_ERR_MSG);
        }
    }

    @Before(value = "@smoke_test,@sanity_test",order = 0)
    public void cleanUpSentFolder(){
        gmailPage.clickSentFolderLink();
        if(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList())) {
            gmailPage.deleteAllEmailsFromFolder();
            gmailPage.clickSentFolderLink();
            Assert.assertFalse(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()), SENT_EMAIL_PRESENCE_ERR_MSG);
        }
    }

}
