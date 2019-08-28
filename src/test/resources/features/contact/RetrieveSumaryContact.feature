@Contact
Feature: Manage contact in Salesforce

  @Acceptance
  Scenario: verify get summary for Contact
    When user retrieves the summary for Contact
    Then the status code is 200
    And the response passes Contact Retrive Sumary Schema validation