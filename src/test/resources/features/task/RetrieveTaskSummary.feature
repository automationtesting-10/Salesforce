Feature: Retrieve summary from Task endpoint

  Scenario: A user wants to retrieve a summary of Task
    When user makes a get request to endpoint
    Then status code is 200
