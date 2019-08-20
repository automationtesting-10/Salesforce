Feature: Acceptance Accounts tests

  Background:
    Given I log in with Authorization token

  Scenario: Get Accounts
    Given I set up a GET request to Account endpoint

  Scenario: Create an Account
    Given I fill the request with the minimun data required
    |Name|test|
    When I create an Account with the name

#  Scenario: Delete an Account
#    Given I fill the delete request
#    When I delete the account that previously was created
