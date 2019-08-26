Feature: Acceptance Accounts tests

  Background:
    Given I log in with Authorization token

  Scenario: Get Accounts
    When I get all accounts created
