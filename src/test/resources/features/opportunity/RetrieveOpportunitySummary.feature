@Opportunity
Feature: Retrieve summary from Opportunity in Salesforce

  Scenario: Read the opportunities.
    When User send request GET to opportunity endpoint
    Then User get a "200" status code as response
      And User verify response in the opportunity retrieve schema
