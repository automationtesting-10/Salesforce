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
  Scenario: Creates an opportunity that the name is 120 characters long
    Given User configures the data with a random name of 120 characters:
      | CloseDate  | 2019-04-04   |
      | StageName  | Prospecting  |
    When User send request POST to opportunity endpoint
    Then User get a "201" status code as response
      And The message of the response is:
        | success | true |
      And User verify response in the opportunity create schema

  @Negative
  Scenario: Creates an opportunity that the name is more than 120 characters long
    Given User configures the data with a random name of 121 characters:
      | CloseDate  | 2019-04-04   |
      | StageName  | Prospecting  |
    When User send request POST to opportunity endpoint
    Then User get a "400" status code as response
    And The message of the response is:
      | errorCode | [STRING_TOO_LONG] |

  @Negative
  Scenario Outline: Creates multiple opportunities by specifying only the required values
    Given User set up the data:
      | Name      | <name>      |
      | CloseDate | 2019-05-05  |
      | StageName | <stageName> |
    When User send request POST to opportunity endpoint
    Then User get a "400" status code as response
      And The message of the response is:
        | errorCode | [REQUIRED_FIELD_MISSING] |
    Examples:
      | name | stageName   |
      |      | Prospecting |
      | work |             |
      |      |             |

  @DeleteOpportunity @Negative
  Scenario: User creates a opportunity by specifying malformed json content
    Given User set up Json content:
    """
        {
          "Name" "TestApi",
	      "CloseDate" "2000-05-08",
	      "StageName" "Prospecting"
        }
    """
    When User send request POST to opportunity endpoint
    Then User get a "400" status code as response
    And The message of the response is:
      | errorCode | [JSON_PARSER_ERROR] |