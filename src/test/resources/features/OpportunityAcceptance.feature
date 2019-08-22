
Feature: Manage Opportunities tests

  @deleteNewOpportunity
  Scenario: User creates a new opportunity with the required data
    Given User set up the data:
      | Name      | test1.0    |
      | CloseDate | 2019-01-01 |
      | StageName | Qualifier  |
    When User send de request post to opportunity endpoint
    Then User get a "201" status code as response
      And The response has been success true
