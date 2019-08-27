Feature: Acceptance Accounts tests

  Scenario: Get case
    When I get all case created
    Then the status code case is a number 200

  @FindCase
  Scenario: Find case by Id with correct Id
    When a user finds an existing case by Id
    Then the status code case is a number 200

  Scenario: Find case by Id that does not exist
    When a user finds a case by Id "00Q3i000002MKLeEA"
    Then the status code case is a number 404