@Opportunity
  Feature: Delete Opportunity in Salesforce

    @CreateOpportunity @Acceptance
    Scenario: Delete a new opportunity that has already been created.
      When User send request DELETE to new opportunity endpoint
      Then the status code is 204

    @Negative
    Scenario: Delete an opportunity that existed before
      When User makes a DELETE request for opportunity 0064P00000lbdzuQAA
      Then the status code is 404
        And the response includes the following
          | message | [entity is deleted] |

    @Negative
    Scenario: Delete a non-existent opportunity
      When User makes a DELETE request for opportunity 00T3i000005aHO0EAM
      Then the status code is 404
      And the response includes the following
        | message | [The requested resource does not exist] |