Feature: Creation funtional and acceptance

  @DeleteCase
  Scenario: Delete a case
    When I delete an case that previusly was created
    Then the status code case is a number 204