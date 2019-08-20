Feature: Find lead by Id
  Scenario: Find lead by Id with correct Id
    When a user retrieves the lead by Id 00Q3i000002MKLeEAO
    Then the status code is 200
    And response includes the following
      | Id	 	        | 00Q3i000002MKLeEAO     |
      | attributes.type	| Lead                   |

  Scenario: Find lead by Id that does not exist
    When a user retrieves the lead by Id 00Q3i000002MKLeEA
    Then the status code is 404

  Scenario: Find lead by Id that is malformed
    When a user retrieves the lead by Id 00Q3i000002MKLeEA1
    Then the status code is 400


