@Opportunity
Feature: Find Opportunity in Salesforce

  @CreateOpportunity @DeleteOpportunity @Acceptance
  Scenario: Find for an existing opportunity
    When User searches for an existing opportunity
    Then the status code is 200
      And the response includes the following
        | attributes.type  | Opportunity |
      And the response passes opportunity find schema validation

