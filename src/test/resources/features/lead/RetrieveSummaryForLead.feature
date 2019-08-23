Feature: Retrieve summary for lead

  Scenario: Verify get summary for lead
    When a user retrieves the summary for lead
    Then the status code is 200
    And headers include the following
      | Vary | Accept-Encoding |
    And response body includes the following
      | objectDescribe.labelPlural | Leads |
      | objectDescribe.name        | Lead  |
