Feature: Create lead
  @LeadCreation
  Scenario: Create a lead sending correct json with all required fields
    Given a user sets json object
      | Company	 	| TestCompany      |
      | LastName	| TestLastName     |
    When the user creates the lead
    Then the status code is 201
    And headers include the following
      | Vary	    | Accept-Encoding   |
    And response includes the following
      | success 	| true         |

  Scenario: Create a lead sending json with required LastName field missing
    Given a user sets json object
      | Company	 	| TestCompany     |
    When the user creates the lead
    Then the status code is 400
    And response contains the following
      | errorCode	| REQUIRED_FIELD_MISSING  |
      | message     | Required fields are missing: [LastName]    |

  Scenario: Create a lead sending json with required Company field missing
    Given a user sets json object
      | LastName	| TestLastName    |
    When the user creates the lead
    Then the status code is 400
    And response contains the following
      | errorCode	| REQUIRED_FIELD_MISSING  |
      | message     | Required fields are missing: [Company]    |

  Scenario: Create a lead sending json with any required field
    Given a user sets json object
      | FirstName	| TestFirstName       |
    When the user creates the lead
    Then the status code is 400
    And response contains the following
      | errorCode	| REQUIRED_FIELD_MISSING  |
      | message     | Required fields are missing: [LastName, Company]    |
