@Lead
Feature: Create lead

  @LeadCreation @Acceptance
  Scenario: Create a lead sending correct json with all required fields
    Given a user sets json object with lead data
      | Company  | TestCompany  |
      | LastName | TestLastName |
    When the user creates the lead
    Then the status code is 201
    And headers include the following
      | Vary | Accept-Encoding |
    And the response includes the following
      | success | true |
    And the response passes lead creation schema validation

  @LeadCreation @Functional
  Scenario: Create a lead sending correct json with all required fields and some extra
    Given a user sets json object with lead data
      | Company         | TestCompany        |
      | LastName        | TestLastName       |
      | City            | TestCity           |
      | Email           | emailtest@test.com |
      | IsUnreadByOwner | true               |
    When the user creates the lead
    Then the status code is 201
    And headers include the following
      | Vary | Accept-Encoding |
    And the response includes the following
      | success | true |
    And the response passes lead creation schema validation

  @LeadCreation @Functional
  Scenario Outline: Create a lead sending all required fields and optional fields with correct data
    Given the user adds an optional field <Field> with <Value>
    When the user creates the lead
    Then the status code is 201
    And the response passes lead creation schema validation
    Examples:
      | Field             | Value                  |
      | City              | TestCity               |
      | Email             | test@test.com          |
      | Phone             | 123123123              |
      | State             | TestState              |
      | Title             | TestTitle              |
      | Jigsaw            | TestJigsaw             |
      | Rating            | Hot                    |
      | Rating            | Warm                   |
      | Rating            | Cold                   |
      | Status            | Open - Not Contacted   |
      | Status            | Working - Contacted    |
      | Status            | Closed - Converted     |
      | Status            | Closed - Not Converted |
      | Street            | TestStreet             |
      | Country           | TestCountry            |
      | OwnerId           | 0053i000001OtatAAC     |
      | Website           | TestWebsite            |
      | Industry          | TestIndustry           |
      | FirstName         | TestFirstName          |
      | LeadSource        | TestLeadSource         |
      | PostalCode        | 12313                  |
      | Salutation        | Mr.                    |
      | Salutation        | Ms.                    |
      | Salutation        | Mrs.                   |
      | Salutation        | Dr.                    |
      | Salutation        | Prof.                  |
      | Description       | TestDescription        |
      | AnnualRevenue     | 123123123              |
      | GeocodeAccuracy   | Address                |
      | GeocodeAccuracy   | NearAddress            |
      | GeocodeAccuracy   | Block                  |
      | GeocodeAccuracy   | Street                 |
      | GeocodeAccuracy   | ExtendedZip            |
      | GeocodeAccuracy   | Zip                    |
      | GeocodeAccuracy   | Neighborhood           |
      | GeocodeAccuracy   | City                   |
      | GeocodeAccuracy   | County                 |
      | GeocodeAccuracy   | State                  |
      | GeocodeAccuracy   | Unknown                |
      | IsUnreadByOwner   | true                   |
      | IsUnreadByOwner   | false                  |
      | NumberOfEmployees | 123123                 |

  @Negative
  Scenario Outline: Create a lead sending all required fields and optional fields with incorrect data
    Given the user adds an optional field <Field> with <Value>
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    Examples:
      | Field           | Value              |
      | Email           | test@testcom       |
      | OwnerId         | 0053i000001OtatAA1 |
      | GeocodeAccuracy | Province           |

  @LeadCreation @Negative @Bug
  Scenario Outline: Create a lead sending all required fields and optional fields with incorrect data
    Given the user adds an optional field <Field> with <Value>
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    Examples:
      | Field      | Value          |
      | Rating     | TestRating     |
      | Status     | TestStatus     |
      | Salutation | TestSalutation |

  @Negative
  Scenario Outline: Create a lead sending required fields and optional fields with incorrect data type
    Given the user adds an optional field <Field> with <Value>
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error schema validation
    Examples:
      | Field             | Value       |
      | AnnualRevenue     | RevenueTest |
      | IsUnreadByOwner   | True        |
      | IsUnreadByOwner   | False       |
      | NumberOfEmployees | testNumber  |

  @Negative
  Scenario: Create a lead sending json with required LastName field missing
    Given a user sets json object with lead data
      | Company | TestCompany |
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    And the response contains the following
      | errorCode | REQUIRED_FIELD_MISSING                  |
      | message   | Required fields are missing: [LastName] |

  @Negative
  Scenario: Create a lead sending json with required Company field missing
    Given a user sets json object with lead data
      | LastName | TestLastName |
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    And the response contains the following
      | errorCode | REQUIRED_FIELD_MISSING                 |
      | message   | Required fields are missing: [Company] |

  @Negative
  Scenario: Create a lead sending json with any required field
    Given a user sets json object with lead data
      | FirstName | TestFirstName |
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    And the response contains the following
      | errorCode | REQUIRED_FIELD_MISSING                           |
      | message   | Required fields are missing: [LastName, Company] |

  @MultipleLeadsCreation @Functional
  Scenario Outline: Create multiple leads sending correct json with all minimum required fields
    Given a user specifies <Company> and <LastName>
    When the user creates the lead
    Then the status code is 201
    And headers include the following
      | Vary | Accept-Encoding |
    And the response includes the following
      | success | true |
    And the response passes lead creation schema validation
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
    And the response passes lead error schema validation
    And the response contains the following
      | errorCode | JSON_PARSER_ERROR |

  @Negative
  Scenario: Create a lead with empty body and not set contentType
    When the user creates the lead
    Then the status code is 415
    And the response passes lead error schema validation
    And the response contains the following
      | errorCode | UNSUPPORTED_MEDIA_TYPE |

  @LeadCreation @Functional
  Scenario Outline: Create a lead sending all required fields and some optional to check maximum characters limit
    Given a user sets a <Field> value with <Maximum> characters limit
    When the user creates the lead
    Then the status code is 201
    And headers include the following
      | Vary | Accept-Encoding |
    And the response includes the following
      | success | true |
    And the response passes lead creation schema validation
    Examples:
      | Field     | Maximum |
      | LastName  | 80      |
      | Jigsaw    | 20      |
      | FirstName | 40      |

  @Negative
  Scenario Outline: Create a lead sending all required fields and some optional surpassing maximum characters limit
    Given a user sets a <Field> value with <Maximum> characters limit
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    And the response contains the following
      | errorCode | STRING_TOO_LONG |
    Examples:
      | Field     | Maximum |
      | LastName  | 81      |
      | Jigsaw    | 21      |
      | FirstName | 41      |

  @LeadCreation @Functional
  Scenario: Create a lead sending all required fields and Latitude and Longitude
    Given a user sets json object with lead data
      | LastName  | TestLastName |
      | Company   | TestCompany  |
      | Latitude  | -17.366435   |
      | Longitude | -66.175709   |
    When the user creates the lead
    Then the status code is 201
    And headers include the following
      | Vary | Accept-Encoding |
    And the response includes the following
      | success | true |
    And the response passes lead creation schema validation

  @Negative
  Scenario: Create a lead sending all required fields and Latitude
    Given a user sets json object with lead data
      | LastName | TestLastName |
      | Company  | TestCompany  |
      | Latitude | -17.366435   |
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    And the response contains the following
      | errorCode | FIELD_INTEGRITY_EXCEPTION              |
      | message   | Longitude value is missing : Longitude |

  @Negative
  Scenario: Create a lead sending all required fields and Longitude
    Given a user sets json object with lead data
      | LastName  | TestLastName |
      | Company   | TestCompany  |
      | Longitude | -66.175709   |
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    And the response contains the following
      | errorCode | FIELD_INTEGRITY_EXCEPTION            |
      | message   | Latitude value is missing : Latitude |

  @Negative
  Scenario: Create a lead sending all required fields and Latitude out of valid range
    Given a user sets json object with lead data
      | LastName  | TestLastName |
      | Company   | TestCompany  |
      | Latitude  | -90.366435   |
      | Longitude | -66.175709   |
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    And the response contains the following
      | errorCode | NUMBER_OUTSIDE_VALID_RANGE |

  @Negative
  Scenario: Create a lead sending all required fields and Longitude out of valid range
    Given a user sets json object with lead data
      | LastName  | TestLastName |
      | Company   | TestCompany  |
      | Latitude  | -17.366435   |
      | Longitude | -180.175709  |
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error fields schema validation
    And the response contains the following
      | errorCode | NUMBER_OUTSIDE_VALID_RANGE |

  @Negative
  Scenario: Create a lead sending all required fields and Latitude and Longitude with invalid data type
    Given a user sets json object with lead data
      | LastName  | TestLastName  |
      | Company   | TestCompany   |
      | Latitude  | TestLatitude  |
      | Longitude | TestLongitude |
    When the user creates the lead
    Then the status code is 400
    And the response passes lead error schema validation
    And the response contains the following
      | errorCode | JSON_PARSER_ERROR |


