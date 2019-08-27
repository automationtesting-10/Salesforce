Feature: Creation funtional and acceptance

  @DeleteCase
  Scenario: Delete a case
    When user deletes a case that previusly was created
    Then the status code case is a number 204