@Opportunity
Feature: Manage Opportunities tests

  @CreateOpportunity @DeleteOpportunity
  Scenario: Update a opportunity that has already been created.
    Given User set up the data:
      | Name | test2.0 |
    When User send request PATCH to new opportunity endpoint
    Then User get a "204" status code as response
