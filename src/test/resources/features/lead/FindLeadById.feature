@Lead
Feature: Find lead by Id

  @FindLead @Acceptance
  Scenario: Find lead by Id with correct Id
    When a user finds an existing lead by Id
    Then the status code is 200
    And the Id in response is the same as the one looked for
    And headers include the following
      | Vary | Accept-Encoding |
    And the response includes the following
      | attributes.type | Lead |

  @Negative
  Scenario: Find lead by Id that does not exist
    When a user finds a lead by Id 00Q3i000002MKLeEA
    Then the status code is 404
    And the response passes lead error schema validation
    And the response contains the following
      | errorCode | NOT_FOUND |

  @Negative
  Scenario: Find lead by Id that is malformed
    When a user finds a lead by Id 00Q3i000002MKLeEA1
    Then the status code is 400
    And the response passes lead error schema validation
    And the response contains the following
      | errorCode | MALFORMED_ID |

  @Negative @Bug
  Scenario: Find lead from another owner
    When a user finds a lead by Id 00Q3i000001QlusEAC
    Then the status code is 404
    And the response passes lead error schema validation
    And the response contains the following
      | errorCode | INVALID_CROSS_REFERENCE_KEY |
