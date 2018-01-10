@functional_test
Feature:
    As a user
    I want to be able compose and send new emails
    So recipients can receive my emails

    @smoke_test
    Scenario: compose new email
        Given a web browser is at the Google mail home page
        When the user clicks compose the email button and fills recipients, the subject, the body
          | automatizationtester1@gmail.com|This is the test mail for automatizationtester1|Some text inside the mail body for automatizationtester1|
        Then the email should be displayed as the first in the draft folder
        And the email fields should be displayed with the equal values as user has entered

    @sanity_test
    Scenario: compose new email with an image addition
        Given a web browser is at the Google mail home page
        When the user clicks compose the email button and fills recipients, the subject, the body, the image
          | automatizationtester1@gmail.com|This is the test mail for automatizationtester1|Some text inside the mail body for automatizationtester1|https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUQ9w2b5mfxHJ7E8n9eK_wXAJonkzdLOsX30qMhJAazDf6fGXd |
        And the user adds an image to the email body
        Then the email should be displayed as the first in the draft folder
        And the email fields should be displayed with the equal values as user has entered

    Scenario: send draft email
        Given draft email is opened and ready for sending
        When user clicks send button
        Then the email shouldn't be displayed in the draft folder
        And the email should be displayed in the sent folder
