Feature: Retrieve summary for lead
  Scenario: Verify get summary for lead
    When a user retrieves the summary for lead
    Then the status code is 200
    And response includes the following
      | objectDescribe.labelPlural  | Leads      |
      | objectDescribe.name         | Lead       |