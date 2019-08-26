Feature: Delete funtional and acceptance

  @DeleteAccount
  Scenario: Delete an Account
    When I delete an account that previusly was created
    Then the status code is a number 204
