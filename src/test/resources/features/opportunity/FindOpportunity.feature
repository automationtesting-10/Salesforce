@Opportunity
Feature: Find Opportunity in Salesforce

  @CreateOpportunity @DeleteOpportunity @Acceptance
  Scenario: Find for an existing opportunity
    When User searches for an existing opportunity
    Then User get a "200" status code as response
      And The message of the response is:
        | attributes.type  | Opportunity |
      And User verify response in the opportunity find schema

