Feature: Delete lead by Id

  @DeleteLead @Acceptance
  Scenario: Delete a lead by correct Id
    When a user deletes an existing lead by Id
    Then the status code is 204

  @Negative
  Scenario: Delete a lead by Id that has been already deleted
    When a user deletes a lead by Id 00Q3i000002MRlqEAG
    Then the status code is 404
    And response contains the following
      | errorCode | ENTITY_IS_DELETED |

  @Negative
  Scenario: Delete a lead by Id that does not exist
    When a user deletes a lead by Id 00Q3i000002MRlqEA
    Then the status code is 404
    And response contains the following
      | errorCode | NOT_FOUND |

  @Negative
  Scenario: Delete a lead by Id that is malformed
    When a user deletes a lead by Id 00Q3i000002MRlqEA1
    Then the status code is 400
    And response contains the following
      | errorCode | MALFORMED_ID |
