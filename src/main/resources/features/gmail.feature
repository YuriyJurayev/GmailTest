@authorization
Feature: gmail

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


    Scenario: GMAIL login
        Given user opens gmail home page
        When user enters credentials
        Then gmail home page is displayed




    Scenario: GMAIL logout
        When user clicks logout button
        Then gmail sign in page is displayed