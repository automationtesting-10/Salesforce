Feature: Manage Tasks in Salesforce
  Background: User is authenticated
    Given a user sing in the web page SalesForce

  @Negative
  Scenario: Delete a Contact by Id that has been already deleted
    When User deletes a Contact by Id 123841239490100903
    Then The status code is 404 contact

  @DeleteContact
  Scenario: Delete a contact by correct Id
    When User deletes an existing contact by Id
    Then The status code is 204 ok delete contact
  @Negative
  Scenario: Find contact by Id that does not exist
    When User finds a contact by Id 002340910190814900
    Then The status code is 404

  @Negative
  Scenario: Find lead by Id that is malformed
    When User finds a contact by Id 0hn893023948293484
    Then the status code is 400
