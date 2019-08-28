@Case
Feature: Modify an existing case

  @UpdateCase  @Acceptance
  Scenario: Update an case sending correct json
    Given an user sets json object for the modify case
      | type | employee |
    When the user updates existing case by Id
    Then the status code is 204
