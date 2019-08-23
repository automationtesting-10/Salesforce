Feature: Manage Tasks in Salesforce
  Background: User is authenticated
    Given a user sing in the web page SalesForce
  @DeleteContact
  Scenario: Delete a contact by correct Id
    When User deletes an existing contact by Id
    Then The status code is 204 ok delete contact