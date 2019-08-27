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

  @DeleteAccount @FindAccount @DeleteOpportunity @Acceptance
  Scenario: Creates a new opportunity with Opportunity Information date.
    Given User set up all the data:
      | Name       | test_2.0                    |
      | CloseDate  | 2019-02-02                  |
      | StageName  | Prospecting                 |
      | Type       | Existing Customer - Upgrade |
      | LeadSource | Web                         |
      | IsPrivate  | true                        |
      | Amount     | 123.0                       |
      | Probability| 30.0                        |
      | NextStep   | right                       |
    When User send request POST to opportunity endpoint
    Then User get a "201" status code as response
      And The message of the response is:
        | success | true |
      And User verify response in the opportunity create schema

  @DeleteAccount @FindAccount @DeleteOpportunity @Functional
  Scenario: Creates a new opportunity with all dates.
    Given User set up all the data:
      | Name                          | test_3.0                    |
      | CloseDate                     | 2019-03-03                  |
      | StageName                     | Qualification                 |
      | Type                          | Existing Customer - Upgrade |
      | LeadSource                    | Web                         |
      | IsPrivate                     | true                        |
      | Amount                        | 456.0                       |
      | Probability                   | 15.0                        |
      | NextStep                      | left                       |
      |Description                    | easy                        |
      | OrderNumber__c                | 1                           |
      | MainCompetitors__c            | 2                           |
      | CurrentGenerators__c          | qwerty                      |
      | DeliveryInstallationStatus__c | In progress                 |
      | TrackingNumber__c             | 22                          |
    When User send request POST to opportunity endpoint
    Then User get a "201" status code as response
      And The message of the response is:
        | success | true |
      And User verify response in the opportunity create schema

  @DeleteOpportunity @Functional
  Scenario Outline: Creates multiple opportunity by specifying at least a Stage Name
    Given User set up the data:
      | Name       | test_4.0     |
      | CloseDate  | 2019-04-04   |
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

  @DeleteOpportunity @Positive
  Scenario: Creates an opportunity that the name is more than 120 characters long
    Given User set up Json content:
    """
        {
	      "Name": "this text is a test to see if 120 characters have been entered in this text, this text is a test to see if 120 character",
	      "CloseDate": "2019-02-02",
	      "StageName": "Closed Won"
        }
    """
    When User send request POST to opportunity endpoint
    Then User get a "201" status code as response
      And The message of the response is:
        | success | true |
      And User verify response in the opportunity create schema

  @Positive @Negative
  Scenario: Creates an opportunity that the name is more than 120 characters long
    Given User set up Json content:
    """
        {
	      "Name": "this text is a test to see if 120 characters have been entered in this text, this text is a test to see if 120 characters",
	      "CloseDate": "2019-02-02",
	      "StageName": "Closed Won"
        }
    """
    When User send request POST to opportunity endpoint
    Then User get a "400" status code as response
    And The message of the response is:
      | errorCode | [STRING_TOO_LONG] |