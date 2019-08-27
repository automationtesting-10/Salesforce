Feature: Manage Tasks in Salesforce
  @Acceptance
  Scenario: Verify get summary for Contact
    When User retrieves the summary for Contact
    Then The status code is 200
