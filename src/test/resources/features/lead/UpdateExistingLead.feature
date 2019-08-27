@Lead
Feature: Update an existing lead

  @UpdateLead @Acceptance
  Scenario: Update a lead sending correct json
    Given a user sets json object
      | Company  | TestCompany  |
      | LastName | TestLastName |
    When the user updates existing lead by Id
    Then the status code is 204

  @Negative
  Scenario: Update a lead by Id that does not exist
    Given a user sets json object
      | Company  | TestCompany  |
      | LastName | TestLastName |
    When the user updates lead by Id 00Q3i000002MQiqEA
    Then the status code is 404
    And response contains the following
      | errorCode | NOT_FOUND |

  @Negative
  Scenario: Update a lead by Id that is malformed
    Given a user sets json object
      | Company  | TestCompany  |
      | LastName | TestLastName |
    When the user updates lead by Id 00Q3i000002MQiqEA1
    Then the status code is 400
    And response contains the following
      | errorCode | MALFORMED_ID |

  @Negative
  Scenario: Update a lead by Id that is deleted
    Given a user sets json object
      | Company  | TestCompany  |
      | LastName | TestLastName |
    When the user updates lead by Id 00Q3i000002MQiqEAG
    Then the status code is 404
    And response contains the following
      | errorCode | ENTITY_IS_DELETED |

  @UpdateLead @Negative
  Scenario: Update a lead with an incorrect json
    Given a user provides the following json
    """
    {
      "Company": "TestCompany"
      "LastName": "TestLastName"
    }
    """
    When the user updates existing lead by Id
    Then the status code is 400
    And response contains the following
      | errorCode | JSON_PARSER_ERROR |

  @UpdateLead @Negative
  Scenario: Update a lead with empty body and not set contentType
    When the user updates existing lead by Id
    Then the status code is 400
    And response contains the following
      | errorCode | INVALID_CONTENT_TYPE |
