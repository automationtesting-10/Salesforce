@Opportunity
Feature: Create Opportunity in Salesforce

  @DeleteOpportunity @Acceptance
  Scenario: Create a new opportunity with the required data.
    Given User set up the data:
      | Name      | test_1.0   |
      | CloseDate | 2019-01-01 |
      | StageName | Qualifier  |
    When User send request POST to opportunity endpoint
    Then User get a "201" status code as response
    And The message of the response is:
      | success | true |
    And User verify response in the opportunity create schema

  @DeleteAccount @FindAccount @DeleteOpportunity
  Scenario: Creates a new opportunity with Opportunity Information date.
    Given User set up all the data:
      | Name       | test_2.0                   |
      | CloseDate  | 2019-02-02                 |
      | StageName  | Prospecting                |
      | Type       | Existing Customer - Upgrade|
      | LeadSource | Web                        |
      | IsPrivate  | true                       |
      | Amount     | 123.0                      |
    When User send request POST to opportunity endpoint
    Then User get a "201" status code as response
    And The message of the response is:
      | success | true |
    And User verify response in the opportunity create schema

  @DeleteOpportunity @Functional
  Scenario Outline: User creates multiple opportunity by specifying at least a Stage Name
    Given User set up the data:
      | Name       | test_3.0     |
      | CloseDate  | 2019-03-03   |
      | StageName  | <StageName>  |
    When User send request POST to opportunity endpoint
    Then User get a "201" status code as response
    And The message of the response is:
      | success | true |
    And User verify response in the opportunity create schema
    Examples:
    | StageName           |
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

    Scenario: