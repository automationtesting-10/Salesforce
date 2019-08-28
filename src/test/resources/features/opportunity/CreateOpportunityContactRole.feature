@Opportunity
Feature: Create Opportunity Contact Role in Salesforce

  @CreateOpportunity @DeleteOpportunity @DeleteContact @CreateContact @Acceptance
  Scenario: Create a new opportunity contact role with the required data.
    Given User set up the data with contact id
    When User send request POST to opportunity contact role endpoint
    Then the status code is 201
      And the response includes the following
        | success | true |
      And the response passes opportunity create schema validation

  @Negative
  Scenario Outline: Create a new opportunity contact role with blank space.
    Given User set up the data:
      | OpportunityId | <opportunityId> |
      | ContactId     | <contactId>     |
    When User send request POST to opportunity contact role endpoint
    Then the status code is 400
      And the response includes the following
        | errorCode | [REQUIRED_FIELD_MISSING] |
      And the response passes opportunity create schema validation
    Examples:
      | opportunityId      | contactId          |
      | 0064P00000lc4BZQAY |                    |
      |                    | 0034P00002aqmJIQAY |
      |                    |                    |
