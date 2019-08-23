
Feature: Manage Opportunities tests

  @deleteNewOpportunity
  Scenario: User creates a new opportunity with the required data
    Given User set up the data:
      | Name      | test1.0    |
      | CloseDate | 2019-01-01 |
      | StageName | Qualifier  |
    When User send request POST to opportunity endpoint
    Then User get a "201" status code as response
      And The response has been success true

  @create_stories
  Scenario: User deletes a new opportunity that has already been created.
    When User send request DELETE to opportunity endpoint
    Then User get a "204" status code as response


  @create_stories
  Scenario: Read the content of specific opportunity
    Given User set up the data
    When User send request get to opportunity endpoint
    Then User get a "201" status code as response
      And I verify the project schema

