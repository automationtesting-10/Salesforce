Feature: Acceptance Accounts tests

  Background:
    Given I log in with Authorization token

  Scenario: Get Accounts
    When I get all accounts created

  @DeleteAccount
  Scenario: Delete an Account
    When I fill the delete request
    Then I delete the account that previously was created is 204
