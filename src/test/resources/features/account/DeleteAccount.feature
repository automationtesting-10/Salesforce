Feature: Delete funtional and acceptance

  @DeleteAccount
  Scenario: Delete an Account
    When I delete an account that previusly was created
    Then I delete the account that previously was created is 204
