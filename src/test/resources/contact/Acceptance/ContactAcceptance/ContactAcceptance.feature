Feature: Manage Tasks in Salesforce

  Background: User is authenticated
    Given a user sing in the web page SalesForce

  Scenario: User creates a new user contact
    Given user specifies new contact
      | lastName   |  menacho   |
#  email   | Title   |
#      | Menacho   |   jesus   | test@123.com   | QA   |

    When User send de request post to contact endpoint
    Then User get a "201" status code

#  Scenario: User wants to delete an existing contact
#    When User makes a delete request for an existing contact
#    Then Status code is 204
#
#  Scenario: User searches for an existing contact
#    When User searches for an existing contact
#    Then Status code is 200
#
#  Scenario: User wants to retrieve a summary of contact
#    When User makes a get request to contact
#    Then Status code is 200
