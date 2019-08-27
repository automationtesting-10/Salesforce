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
    And response passes lead creation schema validation

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
    And response passes lead creation schema validation

  @LeadCreation @Functional
  Scenario Outline: Create a lead sending all required fields and optional fields with correct data
    Given the user adds an optional field <Field> with <Value>
    When the user creates the lead
    Then the status code is 201
    Examples:
      | Field             | Value              |
      | City              | TestCity           |
      | Email             | test@test.com      |
      | Phone             | 123123123          |
      | State             | TestState          |
      | Title             | TestTitle          |
      | Jigsaw            | TestJigsaw         |
      | Rating            | TestRating         |
      | Status            | TestStatus         |
      | Street            | TestStreet         |
      | Country           | TestCountry        |
      | OwnerId           | 0053i000001OtatAAC |
      | Website           | TestWebsite        |
      | Industry          | TestIndustry       |
      | FirstName         | TestFirstName      |
      | LeadSource        | TestLeadSource     |
      | PostalCode        | 12313              |
      | Salutation        | TestSalutation     |
      | Description       | TestDescription    |
      | AnnualRevenue     | 123123123          |
      | GeocodeAccuracy   | Address            |
      | GeocodeAccuracy   | NearAddress        |
      | GeocodeAccuracy   | Block              |
      | GeocodeAccuracy   | Street             |
      | GeocodeAccuracy   | ExtendedZip        |
      | GeocodeAccuracy   | Zip                |
      | GeocodeAccuracy   | Neighborhood       |
      | GeocodeAccuracy   | City               |
      | GeocodeAccuracy   | County             |
      | GeocodeAccuracy   | State              |
      | GeocodeAccuracy   | Unknown            |
      | IsUnreadByOwner   | true               |
      | IsUnreadByOwner   | false              |
      | NumberOfEmployees | 123123             |

  @Negative
  Scenario Outline: Create a lead sending all required fields and optional fields with incorrect data
    Given the user adds an optional field <Field> with <Value>
    When the user creates the lead
    Then the status code is 400
    Examples:
      | Field             | Value              |
      | Email             | test@testcom       |
      | OwnerId           | 0053i000001OtatAA1 |
      | AnnualRevenue     | RevenueTest        |
      | GeocodeAccuracy   | Province           |
      | IsUnreadByOwner   | True               |
      | NumberOfEmployees | testNumber         |

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
    And response passes lead creation schema validation
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

  @Negative
  Scenario: Create a lead with empty body and contentType set
    When the user creates the lead
    Then the status code is 415
    And response contains the following
      | errorCode | UNSUPPORTED_MEDIA_TYPE |
