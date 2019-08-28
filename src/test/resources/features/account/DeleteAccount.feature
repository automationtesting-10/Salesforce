Feature: Delete funtional and acceptance

  @DeleteAccount @Acceptance
  Scenario: Delete an Account
    When user deletes an account that previusly was created
    Then the status code is 204

  @Negative
  Scenario: Delete a account by Id that has been already deleted
    When an user deletes an account by Id "00Q3i000002MRlqEAG"
    Then the status code is 404

  @Negative
  Scenario: Delete a account by Id that is malformed
    When an user deletes an account by Id "00Q3i000002MRlqEA1"
    Then the status code is 404
    And response contains the following message
      | errorCode | NOT_FOUND |
