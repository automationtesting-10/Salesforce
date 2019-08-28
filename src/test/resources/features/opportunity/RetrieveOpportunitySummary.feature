@Opportunity
Feature: Retrieve summary from Opportunity in Salesforce

  Scenario: Read the opportunities.
    When User send request GET to opportunity endpoint
    Then the status code is 200
      And the response passes opportunity retrieve schema validation
