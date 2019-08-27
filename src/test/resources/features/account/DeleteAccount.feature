Feature: Delete funtional and acceptance

  @DeleteAccount
  Scenario: Delete an Account
    When I delete an account that previusly was created
    Then the status code is a number 204

  @Negative
  Scenario: Delete a account by Id that has been already deleted
    When a user deletes a account by Id "00Q3i000002MRlqEAG"
    Then the status code is a number 404

  Scenario: Delete a account by Id that is malformed
    When a user deletes a account by Id "00Q3i000002MRlqEA1"
    Then the status code is a number 404
    And response contains the following message
      | errorCode | NOT_FOUND |