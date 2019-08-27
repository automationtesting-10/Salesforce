@Opportunity
  Feature: Delete Opportunity in Salesforce

    @CreateOpportunity @Acceptance
    Scenario: Delete a new opportunity that has already been created.
      When User send request DELETE to new opportunity endpoint
      Then User get a "204" status code as response

    @Negative
    Scenario: Delete an opportunity that existed before
      When User makes a DELETE request for opportunity 0064P00000lbdzuQAA
      Then User get a "404" status code as response
        And The message of the response is:
          | message | [entity is deleted] |

    @Negative
    Scenario: Delete a non-existent opportunity
      When User makes a DELETE request for opportunity 00T3i000005aHO0EAM
      Then User get a "404" status code as response
      And The message of the response is:
        | message | [The requested resource does not exist] |