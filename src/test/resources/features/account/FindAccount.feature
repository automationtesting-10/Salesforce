Feature: Acceptance Accounts tests

  Scenario: Get Accounts
    When I get all accounts created
    Then the status code is a number 200

  @FindAccount
  Scenario: Find Account by Id with correct Id
    When a user finds an existing account by Id
    Then the status code is a number 200

  Scenario: Find account by Id that does not exist
    When a user finds a account by Id "00Q3i000002MKLeEA"
    Then the status code is a number 404
