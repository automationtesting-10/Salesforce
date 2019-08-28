@Contact
Feature: Manage contact in Salesforce

  @UpdateContact @Acceptance
  Scenario: Update a Contact
    Given user sets the lastName
      | lastName   |  testName22   |
    When user updates existing contact by Id
    Then the status code is 204

  @UpdateContact @Negative
  Scenario: Update a contact by Id that does not exist
    Given user sets lastName to the contact
      | lastName   |  testName22   |
    When user updates contact by Id 00f12s2a11423874
    Then the status code is 404

  @UpdateContact @Negative
  Scenario: Update a contact with an incorrect json
    Given user provides the following json
    """
        {
          "LastName","TestName"
        }
         """
    When user updates existing contact by Id
    Then the status code is 400
