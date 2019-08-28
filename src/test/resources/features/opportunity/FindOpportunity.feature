@Opportunity
Feature: Find Opportunity in Salesforce

  @CreateOpportunity @DeleteOpportunity @Acceptance
  Scenario: Find for an existing opportunity
    When User searches for an existing opportunity
    Then the status code is 200
      And the response includes the following
        | attributes.type  | Opportunity |
      And the response passes opportunity find schema validation

  @Negative
  Scenario: A user searches for a non-existing opportunity
    When User searches for opportunity 0054P000007ts7CQAQ
    Then the status code is 404
      And the response contains the following
        | errorCode | NOT_FOUND |

  @Negative
  Scenario: A user searches for a non-existing opportunity
    When User searches for opportunity 0054P000007ts7CQAQ5
    Then the status code is 404
    And the response contains the following
      | errorCode | NOT_FOUND |
