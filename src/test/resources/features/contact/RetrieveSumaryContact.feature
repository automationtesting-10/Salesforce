Feature: Manage Tasks in Salesforce
  Background: User is authenticated
    Given a user sing in the web page SalesForce
  @Acceptance
  Scenario: Verify get summary for Contact
    When User retrieves the summary for Contact
    Then The status code is 200