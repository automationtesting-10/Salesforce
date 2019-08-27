@Lead
Feature: Update an existing lead

  @UpdateLead @Acceptance
  Scenario: Update a lead sending correct json with required fields for lead creation
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
    And the response contains the following
      | errorCode | NOT_FOUND |

  @Negative
  Scenario: Update a lead by Id that is malformed
    Given a user sets json object
      | Company  | TestCompany  |
      | LastName | TestLastName |
    When the user updates lead by Id 00Q3i000002MQiqEA1
    Then the status code is 400
    And the response contains the following
      | errorCode | MALFORMED_ID |

  @UpdateDeleted @Negative
  Scenario: Update a lead by Id that is deleted
    Given a user sets json object
      | Company  | TestCompany  |
      | LastName | TestLastName |
    When the user updates existing lead by Id
    #When the user updates lead by Id 00Q3i000002MQiqEAG
    Then the status code is 404
    And the response contains the following
      | errorCode | ENTITY_IS_DELETED |
    #debería salir cross reference ñañaña

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
    And the response contains the following
      | errorCode | JSON_PARSER_ERROR |

  @UpdateLead @Negative
  Scenario: Update a lead with empty body and not set contentType
    When the user updates existing lead by Id
    Then the status code is 400
    And the response contains the following
      | errorCode | INVALID_CONTENT_TYPE |

  @Negative
  Scenario: Update a lead from another owner
    Given a user sets json object
      | Company  | TestCompany  |
      | LastName | TestLastName |
    When the user updates lead by Id 00Q3i000001QlusEAC
    Then the status code is 404
    And the response contains the following
      | errorCode |  INVALID_CROSS_REFERENCE_KEY |

  @UpdateLead @Functional
  Scenario Outline: Update a lead sending optional fields for lead creation with correct data
    Given the user adds an optional field <Field> with <Value>
    When the user updates existing lead by Id
    Then the status code is 204
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

  @UpdateLead @Negative
  Scenario Outline: Update a lead sending optional fields for lead creation with incorrect data
    Given the user adds an optional field <Field> with <Value>
    When the user updates existing lead by Id
    Then the status code is 400
    Examples:
      | Field             | Value              |
      | Email             | test@testcom       |
      | OwnerId           | 0053i000001OtatAA1 |
      | AnnualRevenue     | RevenueTest        |
      | GeocodeAccuracy   | Province           |
      | IsUnreadByOwner   | True               |
      | IsUnreadByOwner   | False              |
      | NumberOfEmployees | testNumber         |
