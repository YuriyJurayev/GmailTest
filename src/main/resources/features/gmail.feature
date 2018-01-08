@functional_test
Feature:
    As a user
    I want to be able compose and send new emails
    So recipients can receive my emails

#  Scenario:Running a Full Text Quick Search
#    Given I set search request "iPhone 4S"
#    When I perfom search
#    Then the term query "iPhone 4S" should be the first in the Search Result grid

#  Scenario Outline: Login to GMAIL
#    Given I set search request "<request>"
 #   When I perfom search
#    Then the term query "<request>" should be the first in the Search Result grid

#    Examples:
#      | request        |
#      | iPhone 4S      |
#      | Samsung Galaxy |

    Background:
        Given a web browser is at the Google mail home page
        And   the user is logged in

    @smoke_test
    Scenario: compose new email
        When the user clicks compose the email button and fills recipients, the subject, the body
          | automatizationtester1@gmail.com                                                                         |
          |This is the test mail for automatizationtester1                                                          |
          | Some text inside the mail body for automatizationtester1                                                |
        Then the email should be displayed as the first in the draft folder
        And the email fields should be displayed with the equal values as user has entered


    @sanity_test
    Scenario: compose new email with an image addition
        When the user clicks compose the email button and fills recipients, the subject, the body, the image
          | automatizationtester1@gmail.com                                                                         |
          |This is the test mail for automatizationtester1                                                          |
          | Some text inside the mail body for automatizationtester1                                                |
          |https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUQ9w2b5mfxHJ7E8n9eK_wXAJonkzdLOsX30qMhJAazDf6fGXd |
        And the user adds an image to the email body
        Then the email should be displayed as the first in the draft folder
        And the email fields should be displayed with the equal values as user has entered


    Scenario: send draft email
        Given draft email is opened and ready for sending
        When user clicks send button
        Then the email shouldn't be displayed in the draft folder
        And the email should be displayed in the sent folder

    Scenario: logout from mailbox
        Given the user should be logged in
        When user clicks logout button
        Then gmail sign in page is displayed