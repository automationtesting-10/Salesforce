@Lead
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
    And response passes lead schema validation

  @LeadCreation @Functional
  Scenario: Create a lead sending correct json with all required fields and some extra
    Given a user sets json object
      | Company         | TestCompany        |
      | LastName        | TestLastName       |
      | City            | TestCity           |
      | Email           | emailtest@test.com |
      | IsUnreadByOwner | true               |
    When the user creates the lead
    Then the status code is 201
    And headers include the following
      | Vary | Accept-Encoding |
    And response body includes the following
      | success | true |
    And response passes lead schema validation

  @LeadCreation @Functional
  Scenario Outline: Create a lead sending all required fields and optional fields with incorrect data type
    Given the user adds an optional field <Field> with <Value>
    When the user creates the lead
    Then the status code is 400
    Examples:
      | Field              | Value     |
      | City               | 1213245   |
      | Email              | 124124    |
      | Phone              | 123123123 |
      | State              | 213123123 |
      | Title              | 123123123 |
      | Jigsaw             | 123123123 |
      | Rating             | 123123123 |
      | Status             | 123123123 |
      | Street             | 123123123 |
      | Country            | 12313123  |
      | OwnerId            | 123123123 |
      | Website            | 123123123 |
      | Industry           | 123123    |
      | Latitude           | 12313     |
      | FirstName          | 12313     |
      | Longitude          | 12313     |
      | LeadSource         | 12313     |
      | PostalCode         | 1231313   |
      | Salutation         | 12313     |
      | Description        | 123123    |
      | AnnualRevenue      | 123123123 |
      | GeocodeAccuracy    | 1231231   |
      | IsUnreadByOwner    | 123123    |
      | EmailBouncedDate   | 123123123 |
      | NumberOfEmployees  | 123123    |
      | EmailBouncedReason | 12313     |

  @Negative
  Scenario: Create a lead sending json with required LastName field missing
    Given a user sets json object
      | Company | TestCompany |
    When the user creates the lead
    Then the status code is 400
    And response contains the following
      | errorCode | REQUIRED_FIELD_MISSING                  |
      | message   | Required fields are missing: [LastName] |

  @Negative
  Scenario: Create a lead sending json with required Company field missing
    Given a user sets json object
      | LastName | TestLastName |
    When the user creates the lead
    Then the status code is 400
    And response contains the following
      | errorCode | REQUIRED_FIELD_MISSING                 |
      | message   | Required fields are missing: [Company] |

  @Negative
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
    And headers include the following
      | Vary | Accept-Encoding |
    And response body includes the following
      | success | true |
    And response passes lead schema validation
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

  @Negative
  Scenario: Create a lead with empty body and not set contentType
    When the user creates the lead
    Then the status code is 415
    And response contains the following
      | errorCode | UNSUPPORTED_MEDIA_TYPE |
