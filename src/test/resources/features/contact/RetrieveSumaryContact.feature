Feature: Manage Tasks in Salesforce

  @Acceptance
  Scenario: verify get summary for Contact
    When user retrieves the summary for Contact
    Then the status code is 200
