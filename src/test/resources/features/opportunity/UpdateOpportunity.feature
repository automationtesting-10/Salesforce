@Opportunity
Feature: Update Opportunity in Salesforce

  @CreateOpportunity @DeleteOpportunity @Acceptance
  Scenario: Update only name for an opportunity that has already been created.
    Given User set up the data:
      | Name| Test_AT10 |
    When User send request PATCH to new opportunity endpoint
    Then the status code is 204

  @CreateOpportunity @DeleteOpportunity
  Scenario Outline: Update the required values for an opportunity that has already been created.
    Given User configures the data with a random name:
      | CloseDate  | 2019-06-06  |
      | StageName  | <stageName> |
    When User send request PATCH to new opportunity endpoint
    Then the status code is 204
    Examples:
      | stageName           |
      | Prospecting         |
      | Qualification       |
      | Needs Analysis      |
      | Value Proposition   |
      | Id. Decision Makers |
      | Perception Analysis |
      | Proposal/Price Quote|
      | Negotiation/Review  |
      | Closed Won          |
      | Closed Lost         |

  @CreateOpportunity @DeleteOpportunity
  Scenario Outline: Update only closeDate for an opportunity that has already been created.
    Given User set up the data:
      | CloseDate | <closeDate> |
    When User send request PATCH to new opportunity endpoint
    Then the status code is 204
    Examples:
      | closeDate  |
      | 2019       |
      | 2030-02-30 |
      | 2025-10    |
