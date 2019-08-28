@Task
Feature: Retrieve summary from Task endpoint

  Scenario: A user wants to retrieve a summary of Task
    When user makes a get request to endpoint
    Then the status code is 200
    And the response passes task retrieve 200 schema validation
