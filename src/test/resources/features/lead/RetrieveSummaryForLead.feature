@Lead
Feature: Retrieve summary for lead

  @Acceptance
  Scenario: Verify get summary for lead
    When a user retrieves the summary for lead
    Then the status code is 200
    And headers include the following
      | Vary | Accept-Encoding |
    And the response passes lead empty summary schema validation
    And the response includes the following
      | objectDescribe.labelPlural | Leads |
      | objectDescribe.name        | Lead  |

  @LeadSummary @Acceptance
  Scenario: Verify get summary for lead
    When a user retrieves the summary for lead
    Then the status code is 200
    And headers include the following
      | Vary | Accept-Encoding |
    And the response passes lead summary schema validation
    And the response includes the following
      | objectDescribe.labelPlural | Leads |
      | objectDescribe.name        | Lead  |
