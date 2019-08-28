Feature: Manage Tasks in Salesforce

  @FindContact @Acceptance
  Scenario: find Contact by Id
    When user finds an existing Contact by Id
    Then the status code is 200
