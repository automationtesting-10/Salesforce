Feature: Manage Tasks in Salesforce

  @UpdateContact @Acceptance
  Scenario: Update a Contact
    Given User sets the lastName
      | lastName   |  testName22   |
    When User updates existing contact by Id
    Then The status code is 204

  @UpdateContact @Negative
  Scenario: Update a contact by Id that does not exist
    Given User sets lastName to the contact
      | lastName   |  testName22   |
    When User updates contact by Id 00f12s2a11423874
    Then The status code is 404

  @UpdateContact @Negative
  Scenario: Update a contact with an incorrect json
    Given User provides the following json
    """
        {
          "LastName","TestName"
        }
         """
    When User updates existing contact by Id
    Then The status code is 400
