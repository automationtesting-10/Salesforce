@Account
Feature: Acceptance Accounts tests

  @Acceptance
  Scenario: Get Accounts
    When user gets all accounts created
    Then the status code is 200

  @FindAccount @Acceptance
  Scenario: Find Account by Id with correct Id
    When an user finds an existing account by Id
    Then the status code is 200

  @Negative
  Scenario: Find account by Id that does not exist
    When an user finds an account by Id "00Q3i000002MKLeEA"
    Then the status code is 404
