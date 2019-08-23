Feature: Create lead

  @LeadCreation @Acceptance
  Scenario: Create a lead sending correct json with all required fields
    Given a user sets json object
      | Company  | TestCompany  |
      | LastName | TestLastName |
    When the user creates the lead
    Then the status code is 201
    And headers include the following
      | Vary | Accept-Encoding |
    And response body includes the following
      | success | true |

  @Functional
  Scenario: Create a lead sending json with required LastName field missing
    Given a user sets json object
      | Company | TestCompany |
    When the user creates the lead
    Then the status code is 400
    And response contains the following
      | errorCode | REQUIRED_FIELD_MISSING                  |
      | message   | Required fields are missing: [LastName] |

  @Functional
  Scenario: Create a lead sending json with required Company field missing
    Given a user sets json object
      | LastName | TestLastName |
    When the user creates the lead
    Then the status code is 400
    And response contains the following
      | errorCode | REQUIRED_FIELD_MISSING                 |
      | message   | Required fields are missing: [Company] |

  @Functional
  Scenario: Create a lead sending json with any required field
    Given a user sets json object
      | FirstName | TestFirstName |
    When the user creates the lead
    Then the status code is 400
    And response contains the following
      | errorCode | REQUIRED_FIELD_MISSING                           |
      | message   | Required fields are missing: [LastName, Company] |

  @MultipleLeadsCreation @Functional
  Scenario Outline: Create multiple leads sending correct json with all minimum required fields
    Given a user specifies <Company> and <LastName>
    When the user creates the lead
    Then the status code is 201
    Examples:
      | Company      | LastName      |
      | TestCompany1 | TestLastName1 |
      | TestCompany2 | TestLastName2 |
      | TestCompany3 | TestLastName3 |
      | TestCompany4 | TestLastName4 |
      | TestCompany5 | TestLastName5 |

  @Negative
  Scenario: Create a lead with an incorrect json
    Given a user provides the following json
    """
    {
      "Company": "TestCompany"
      "LastName": "TestLastName"
    }
    """
    When the user creates the lead
    Then the status code is 400
    And response contains the following
      | errorCode | JSON_PARSER_ERROR |
