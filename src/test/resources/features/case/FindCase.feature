Feature: Acceptance Accounts tests

  Scenario: Get case
    When user gets all case created
    Then the status code case is a number 200

  @FindCase
  Scenario: Find case by Id with correct Id
    When an user finds an existing case by Id
    Then the status code is 200

  Scenario: Find case by Id that does not exist
    When an user finds a case by Id "00Q3i000002MKLeEA"
    Then the status code is 404
