@authorization
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


    Scenario: login to mailbox
        Given user opens gmail home page
        When user enters credentials
        Then gmail home page is displayed


    Scenario Outline: compose new email
        Given user clicks compose email button and fills recipients "<recipients>",subject "<subject>",body "<body>",image "<image>"
        When user adds an image to email body
        Then draft email is displayed in draft folder
      Examples:
            | recipients                      |  subject         | body| image|
            | automatizationtester1@gmail.com | This is the test mail for automatizationtester1 | Some text inside the mail body for automatizationtester1 | https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUQ9w2b5mfxHJ7E8n9eK_wXAJonkzdLOsX30qMhJAazDf6fGXd |

    Scenario: verify
      Given user opens gmail home page
      When user enters credentials
      Then gmail home page is displayed
   # Scenario: logout from mailbox
    #    When user clicks logout button
     #   Then gmail sign in page is displayed