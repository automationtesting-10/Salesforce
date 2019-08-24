
Feature: Manage Opportunities tests

  @deleteNewOpportunity
  Scenario: Creates a new opportunity with the required data.
    Given User set up the data:
      | Name      | test1.0    |
      | CloseDate | 2019-01-01 |
      | StageName | Qualifier  |
    When User send request POST to opportunity endpoint
    Then User get a "201" status code as response
      And The message of the response is:
        | success | true |
#      And User verify response in the opportunity scheme

  @createNewOpportunity
  Scenario: Delete a new opportunity that has already been created.
    When User send request DELETE to new opportunity endpoint
    Then User get a "204" status code as response

  Scenario: Read the opportunities.
    When User send request GET to opportunity endpoint
    Then User get a "200" status code as response
#      And User verify the project schema

  @createNewOpportunity
  Scenario: Update a opportunity that has already been created.
    Given User set up the data:
      | Name | test2.0 |
    When User send request PATCH to new opportunity endpoint
    Then User get a "204" status code as response