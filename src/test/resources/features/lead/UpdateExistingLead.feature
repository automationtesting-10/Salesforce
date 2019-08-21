Feature: Update an existing lead
  @UpdateLead
  Scenario: Update a lead sending correct json
    Given a user sets json object
      | Company	 	| KingPin5     |
      | LastName	| Mariana      |
    When the user updates existing lead by Id
    Then the status code is 204

  Scenario: Update a lead by Id that does not exist
    Given a user sets json object
      | Company	 	| KingPin5     |
      | LastName	| Mariana      |
    When the user updates lead by Id 00Q3i000002MQiqEA
    Then the status code is 404
    And response contains the following
      | errorCode	| NOT_FOUND    |

  Scenario: Update a lead by Id that is malformed
    Given a user sets json object
      | Company	 	| KingPin5     |
      | LastName	| Mariana      |
    When the user updates lead by Id 00Q3i000002MQiqEA1
    Then the status code is 400
    And response contains the following
      | errorCode	| MALFORMED_ID |

  Scenario: Update a lead by Id that is deleted
    Given a user sets json object
      | Company	 	| KingPin5     |
      | LastName	| Mariana      |
    When the user updates lead by Id 00Q3i000002MQiqEAG
    Then the status code is 404
    And response contains the following
      | errorCode	| ENTITY_IS_DELETED |