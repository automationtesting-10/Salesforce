@Opportunity
Feature: Manage Opportunities tests

  @CreateOpportunity @DeleteOpportunity
  Scenario: Delete a new opportunity that has already been created.
    When User send request DELETE to new opportunity endpoint
    Then User get a "204" status code as response

  Scenario: Read the opportunities.
    When User send request GET to opportunity endpoint
    Then User get a "200" status code as response
#      And User verify the project schema

  @CreateOpportunity @DeleteOpportunity
  Scenario: Update a opportunity that has already been created.
    Given User set up the data:
      | Name | test2.0 |
    When User send request PATCH to new opportunity endpoint
    Then User get a "204" status code as response
