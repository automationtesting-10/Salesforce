@Opportunity
  Feature: Delete Opportunity in Salesforce

    @CreateOpportunity
    Scenario: Delete a new opportunity that has already been created.
      When User send request DELETE to new opportunity endpoint
      Then User get a "204" status code as response

    @Negative
    Scenario: Delete a non-existent opportunity
      When User makes a DELETE request for opportunity 0064P00000lbdzuQAA
      Then User get a "404" status code as response
