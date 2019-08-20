Feature: Acceptance Accounts tests

  Background:
    Given I log in with Authorization token

  Scenario: Get Accounts
    Given I set up a GET request to Account endpoint

  Scenario: Create an Account
    Given I fill the request with the minimun data required
      | Name | Atest002 |
    When I create an Account with the name

  Scenario: Delete an Account
    When I fill the delete request
    Then I delete the account that previously was created is 204
