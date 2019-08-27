Feature: Manage Tasks in Salesforce

  @FindContact @Acceptance
  Scenario: Find Contact by Id
    When User finds an existing Contact by Id
    Then The status code is 200