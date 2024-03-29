@Contact
Feature: Manage contact in Salesforce

  @Negative
  Scenario: delete a Contact by Id that entity is not deleted
    When user finds a contact by Id 0033i000005UeY2AAK
    Then the status code is 404
    And the response includes the following
      | errorCode | [ENTITY_IS_DELETED] |
    And the response passes Contact Delete ID Schema validation

  @Negative
  Scenario: Find contact by Id that does not exist
    When user finds a contact by Id 1133i000005UeY2AAK
    Then the status code is 404
    And the response includes the following
      | errorCode | [NOT_FOUND] |
    And the response passes Contact Delete ID Not Exits Schema validation

  @DeleteContact @Acceptance
  Scenario: delete a contact by correct Id
    When user deletes an existing contact by Id
    Then the status code is 204
