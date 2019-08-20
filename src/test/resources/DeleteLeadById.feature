Feature: Delete lead by Id
  Scenario: Delete a lead by correct Id
    When a user deletes a lead by Id 00Q3i000002MKLeEAO
    Then the status code is 204

  Scenario: Delete a lead by Id that has been already deleted
    When a user deletes a lead by Id 00Q3i000002MKLeEAO
    Then the status code is 404
    And response contains the following
     | errorCode	 	| ENTITY_IS_DELETED   |

  Scenario: Delete a lead by Id that does not exist
    When a user deletes a lead by Id 00Q3i000002MKLeEA
    Then the status code is 404
    And response contains the following
     | errorCode	 	| NOT_FOUND    |

  Scenario: Delete a lead by Id that is malformed
    When a user deletes a lead by Id 00Q3i000002MKLeEA1
    Then the status code is 400
    And response contains the following
     | errorCode 	| MALFORMED_ID   |