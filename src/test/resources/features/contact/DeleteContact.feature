Feature: Manage Tasks in Salesforce
  @Negative
  Scenario: Delete a Contact by Id that entity is deleted
    When User finds a contact by Id 0033i000005UeY2AAK
    Then The status code is 404
    And response contains the following in contact
      | errorCode | [ENTITY_IS_DELETED] |

  @Negative
  Scenario: Find contact by Id that does not exist
    When User finds a contact by Id 1133i000005UeY2AAK
    Then The status code is 404
    And response contains the following in contact
      | errorCode | [NOT_FOUND] |


  @DeleteContact @Acceptance
  Scenario: Delete a contact by correct Id
    When User deletes an existing contact by Id
    Then The status code is 204