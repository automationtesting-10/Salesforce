@Task
Feature: Find specific Task in Salesforce

  @FindTask @Acceptance
  Scenario: A user searches for an existing task
    When user searches for an existing task
    Then the status code is 200
    And the response includes the following
      | attributes.type  | Task        |
    And the response passes task find 200 schema validation

  @FindTask @Acceptance
  Scenario: A user searches for an existing task
    When user searches for an existing task
    Then the status code is 200
    And the response passes task find 200 schema validation

  @Negative
  Scenario: A user searches for a non-existing task
    When user searches for task 00T3i000006I3GqEAK
    Then the status code is 404
