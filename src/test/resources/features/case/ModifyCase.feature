Feature: Modify an existing case

  @UpdateCase
  Scenario: Update an case sending correct json
    Given an user sets json object for the modify case
      | type | employee |
    When the user updates existing case by Id
    Then the status code case is a number 204
