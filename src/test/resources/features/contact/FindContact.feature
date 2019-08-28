@Contact
Feature: Manage contact in Salesforce

  @FindContact @Acceptance
  Scenario: find Contact by Id
    When user finds an existing Contact by Id
    Then the status code is 200

  @Acceptance
  Scenario: find Contact by Id that is malformed
    When user finds a contact by id 0033i000005Uv3DAAs
    Then the status code is 400
    And the response passes Contact Find Malformed Schema validation

  @Negative
  Scenario: find Contact by Id that not exist
    When user finds a contact by id 0033i000005Uv3DAA
    Then the status code is 404
    And the response passes Contact Find Notexit Schema validation

