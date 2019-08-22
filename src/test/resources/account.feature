Feature: Acceptance Accounts tests

  Background:
    Given I log in with Authorization token

  Scenario: Get Accounts
    Given I set up a GET request to Account endpoint

  @AccountCreation
  Scenario: Create an Account
    Given I fill the request with the minimun data required
      | Name | Account536 |
    When I create an Account with the name
    Then the status code is 201
    And " account creation " is valid

  @DeleteAccount
  Scenario: Delete an Account
    When I fill the delete request
    Then I delete the account that previously was created is 204

