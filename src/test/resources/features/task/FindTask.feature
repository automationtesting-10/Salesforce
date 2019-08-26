Feature: Find specific Task in Salesforce

  @FindTask @Acceptance
  Scenario: A user searches for an existing task
    When user searches for an existing task
    Then status code is 200
    And response includes the following
      | attributes.type  | Task        |
    And response complies task find 200 schema

  @FindTask @Acceptance
  Scenario: A user searches for an existing task
    When user searches for an existing task
    Then status code is 200
    And response complies task find 200 schema

  @Negative
  Scenario: A user searches for a non-existing task
    When user searches for task 00T3i000006I3GqEAK
    Then status code is 404
