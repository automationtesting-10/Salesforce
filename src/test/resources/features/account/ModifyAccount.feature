Feature: Modify an existing Account

  @UpdateAccount
  Scenario: Update an account sending correct json
    Given a user sets json object for the modify account
      | Name | employee |
    When the user updates existing account by Id
    Then the status code is a number 204
